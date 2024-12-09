package com.recipes.recipes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.recipes.recipes.controller.CommentController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddComment_Success() throws Exception {
        mockMvc.perform(post("/add-comment")
                .param("username", "John Doe")
                .param("comment", "This is a great recipe!")
                .with(user("user").password("password").roles("USER"))
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/recipes")); // Asegúrate de que esta URL sea correcta
    }

    @Test
    public void testAddComment_EmptyUsername() throws Exception {
        mockMvc.perform(post("/add-comment")
                .param("username", "")
                .param("comment", "This is a great recipe!")
                .with(user("user").password("password").roles("USER"))
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/recipes")); // Asegúrate de que esta URL sea correcta
    }

    @Test
    public void testAddComment_EmptyComment() throws Exception {
        mockMvc.perform(post("/add-comment")
                .param("username", "John Doe")
                .param("comment", "")
                .with(user("user").password("password").roles("USER"))
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/recipes")); // Asegúrate de que esta URL sea correcta
    }
}