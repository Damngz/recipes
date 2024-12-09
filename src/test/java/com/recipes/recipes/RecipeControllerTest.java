package com.recipes.recipes;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
public class RecipeControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testGetRecipeDetail() throws Exception {
    mockMvc.perform(get("/recetas/1"))
      .andExpect(status().isOk())
      .andExpect(view().name("recipe"))
      .andExpect(model().attributeExists("recipe"))
      .andExpect(model().attribute("recipe", hasProperty("title", is("Ragout de ternera"))));
  }

  @Test
  public void testAddComment() throws Exception {
    mockMvc.perform(post("/recetas/1/comments")
        .param("author", "John Doe")
        .param("text", "Excelente receta")
        .param("rating", "5")
        .with(user("user").password("password").roles("USER"))
        .with(csrf()))
      .andExpect(status().is3xxRedirection())
      .andExpect(redirectedUrl("/recetas/1"));
  }
}
