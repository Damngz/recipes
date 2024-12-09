package com.recipes.recipes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.recipes.recipes.model.Recipe;
import com.recipes.recipes.service.RecipeService;

import java.util.List;

public class RecipeServiceTest {
    private RecipeService recipeService;

    @BeforeEach
    void setUp() {
      recipeService = new RecipeService();
    }

    @Test
    void testGetAllRecipes() {
      List<Recipe> recipes = recipeService.getAllRecipes();
      assertNotNull(recipes, "La lista de recetas no debe ser nula");
      assertFalse(recipes.isEmpty(), "La lista de recetas no debe estar vacía");
      assertEquals(6, recipes.size(), "El tamaño de la lista de recetas debe ser 6");
    }
    
    @Test
    void testSearchRecipes() {
      List<Recipe> recipesFiltered = recipeService.searchRecipes("carne");
      assertEquals(3, recipesFiltered.size());
    }
}
