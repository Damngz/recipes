package com.recipes.recipes.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
  private TokenStore tokenStore;

  public CustomAuthenticationProvider(TokenStore tokenStore) {
    super();
    this.tokenStore = tokenStore;
  }

  @Override
  public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
    final String name = authentication.getName();
    final String password = authentication.getCredentials().toString();
    final var restTemplate = new RestTemplate();
    final var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    Map<String, String> requestBody = new HashMap<>();
    
    try {
      requestBody.put("email", name);
      requestBody.put("password", password);
      final var responseEntity = restTemplate.postForEntity("http://localhost:8081/login", requestBody, String.class);

      if (responseEntity.getStatusCode() != HttpStatus.OK) {
        throw new BadCredentialsException("Invalid credentials");
      }
  
      tokenStore.setToken(responseEntity.getBody());
      
      List<GrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
  
      Authentication authenticatedToken = new UsernamePasswordAuthenticationToken(name, password, authorities);
  
      return authenticatedToken;
    } catch (HttpClientErrorException e) {
      throw new BadCredentialsException("Invalid credentials");
    }
  }

  @Override
  public boolean supports(@SuppressWarnings("rawtypes") Class authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
