package com.recipes.recipes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.client.RestTemplate;

import com.recipes.recipes.security.CustomAuthenticationProvider;
import com.recipes.recipes.security.TokenStore;

import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CustomAuthenticationProviderTest {
  @InjectMocks
  private CustomAuthenticationProvider customAuthenticationProvider;

  @Mock
  private TokenStore tokenStore;

  @Mock
  private RestTemplate restTemplate;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void authenticate_ShouldReturnAuthenticatedToken_WhenValidCredentials() {
    String username = "dmunoz@recipes.cl";
    String password = "11992288Hc-";
    Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

    String validToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJkbXVub3pAcmVjaXBlcy5jbCIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTczMzY5ODczMiwiZXhwIjoxNzMzNzg1MTMyfQ.aOr8HIp4m01qedusgiMdinDeQdVYYQw5aVqdOt8WmcM";
    ResponseEntity<String> responseEntity = new ResponseEntity<>(validToken, HttpStatus.OK);
    when(restTemplate.postForEntity(eq("http://localhost:8081/login"), any(), eq(String.class)))
      .thenReturn(responseEntity);

    Authentication result = customAuthenticationProvider.authenticate(authentication);

    assertNotNull(result);
    assertTrue(result instanceof UsernamePasswordAuthenticationToken);
    assertEquals(username, result.getName());
    assertEquals(password, result.getCredentials());
    assertTrue(result.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));

    ArgumentCaptor<String> tokenCaptor = ArgumentCaptor.forClass(String.class);
    verify(tokenStore).setToken(tokenCaptor.capture());

    String capturedToken = tokenCaptor.getValue();
    assertNotNull(capturedToken);
    assertTrue(capturedToken.matches("^[A-Za-z0-9-._~+/]+=*$"));
  }


  @Test
  void authenticate_ShouldThrowBadCredentialsException_WhenInvalidCredentials() {
    String username = "test@example.com";
    String password = "password";
    Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

    ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    when(restTemplate.postForEntity(eq("http://localhost:8081/login"), any(), eq(String.class)))
      .thenReturn(responseEntity);

    BadCredentialsException exception = assertThrows(BadCredentialsException.class, () -> {
      customAuthenticationProvider.authenticate(authentication);
    });
    assertEquals("Invalid credentials", exception.getMessage());
  }

  @Test
  void authenticate_ShouldThrowBadCredentialsException_WhenHttpClientErrorException() {
    String username = "test@example.com";
    String password = "password";
    Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

    when(restTemplate.postForEntity(eq("http://localhost:8081/login"), any(), eq(String.class)))
      .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

    BadCredentialsException exception = assertThrows(BadCredentialsException.class, () -> {
      customAuthenticationProvider.authenticate(authentication);
    });
    assertEquals("Invalid credentials", exception.getMessage());
  }

  @Test
  void supports_ShouldReturnTrue_WhenCorrectAuthenticationType() {
    boolean result = customAuthenticationProvider.supports(UsernamePasswordAuthenticationToken.class);

    assertTrue(result);
  }

  @Test
  void supports_ShouldReturnFalse_WhenIncorrectAuthenticationType() {
    boolean result = customAuthenticationProvider.supports(String.class);

    assertFalse(result);
  }
}
