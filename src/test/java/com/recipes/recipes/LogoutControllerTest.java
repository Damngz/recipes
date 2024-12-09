package com.recipes.recipes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LogoutControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  @WithMockUser(username = "testuser", roles = {"USER"})
  public void testLogout() throws Exception {
    this.mockMvc.perform(get("/logout"))
      .andExpect(status().is3xxRedirection())
      .andExpect(redirectedUrl("/"));
  }
}
