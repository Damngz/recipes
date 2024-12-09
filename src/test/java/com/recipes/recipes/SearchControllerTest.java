package com.recipes.recipes;

import com.recipes.recipes.controller.SearchController;
import com.recipes.recipes.model.Recipe;
import com.recipes.recipes.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(SearchController.class) // Esta anotación permite que solo el controlador sea cargado
public class SearchControllerTest {

    @Autowired
    private MockMvc mockMvc; // Se inyecta MockMvc para realizar peticiones HTTP simuladas

    @MockBean // Se usa MockBean para simular el servicio
    private RecipeService recipeService;

    private List<Recipe> recipes;

    @BeforeEach
    public void setUp() {
        // Inicializar las recetas de ejemplo
        recipes = Arrays.asList(
                new Recipe(1L, "Spaghetti Bolognese", "url1", 30, 2, 4, "Spaghetti, Tomato, Beef", 
                           "Delicious Italian dish", "Cook spaghetti and add sauce", "Italy", "videoUrl1"),
                new Recipe(2L, "Sushi", "url2", 20, 3, 2, "Rice, Fish, Seaweed", 
                           "Traditional Japanese dish", "Prepare rice and roll with fish", "Japan", "videoUrl2"),
                new Recipe(3L, "Tacos", "url3", 15, 1, 4, "Tortilla, Beef, Lettuce", 
                           "Popular Mexican dish", "Cook beef and assemble taco", "Mexico", "videoUrl3")
        );
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void testSearchController_withQuery() throws Exception {
        // Mocking del servicio para retornar recetas filtradas
        when(recipeService.searchRecipes("spaghetti")).thenReturn(Arrays.asList(recipes.get(0)));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/buscar")
                .param("query", "spaghetti"))
                .andExpect(status().isOk())
                .andExpect(view().name("search"))
                .andExpect(model().attributeExists("recipes"))
                .andExpect(model().attribute("query", "spaghetti"))
                .andReturn();

        // Verifica que las recetas pasadas al modelo son las correctas
        @SuppressWarnings("unchecked")
        List<Recipe> modelRecipes = (List<Recipe>) result.getModelAndView().getModel().get("recipes");
        assertEquals(1, modelRecipes.size());
        assertEquals("Spaghetti Bolognese", modelRecipes.get(0).getTitle());
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void testSearchController_emptyQuery() throws Exception {
        // Mocking del servicio para retornar todas las recetas
        when(recipeService.getAllRecipes()).thenReturn(recipes);

        mockMvc.perform(MockMvcRequestBuilders.get("/buscar")
                .param("query", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("search"))
                .andExpect(model().attributeExists("recipes"))
                .andExpect(model().attribute("query", ""))
                .andReturn();
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void testSearchController_noQuery() throws Exception {
        // Mocking del servicio para retornar todas las recetas
        when(recipeService.getAllRecipes()).thenReturn(recipes);

        mockMvc.perform(MockMvcRequestBuilders.get("/buscar"))
                .andExpect(status().isOk())
                .andExpect(view().name("search"))
                .andExpect(model().attributeExists("recipes"))
                .andReturn();
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void testSearchController_noResults() throws Exception {
        // Mocking del servicio para retornar una lista vacía
        when(recipeService.searchRecipes("pizza")).thenReturn(Arrays.asList());

        mockMvc.perform(MockMvcRequestBuilders.get("/buscar")
                .param("query", "pizza"))
                .andExpect(status().isOk())
                .andExpect(view().name("search"))
                .andExpect(model().attributeExists("recipes"))
                .andExpect(model().attribute("query", "pizza"))
                .andReturn();
    }
}

