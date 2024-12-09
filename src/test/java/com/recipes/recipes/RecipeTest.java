package com.recipes.recipes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.recipes.recipes.model.Comment;
import com.recipes.recipes.model.Recipe;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    private Recipe recipe;

    @BeforeEach
    void setUp() {
        // Inicializamos el objeto Recipe antes de cada prueba
        recipe = new Recipe(1L, "Spaghetti", "image_url", 30, 2, 4, "pasta, sauce", "A delicious spaghetti recipe", "Boil pasta, cook sauce", "Italy", "video_url");
    }

    @Test
    void testGettersAndSetters() {
        // Test para verificar los setters y getters de todos los campos

        // Verificamos el setter y getter de recipeId
        recipe.setRecipeId(2L);
        assertEquals(2L, recipe.getRecipeId());

        // Verificamos el setter y getter de title
        recipe.setTitle("Updated Spaghetti");
        assertEquals("Updated Spaghetti", recipe.getTitle());

        // Verificamos el setter y getter de imageUrl
        recipe.setImageUrl("new_image_url");
        assertEquals("new_image_url", recipe.getImageUrl());

        // Verificamos el setter y getter de time
        recipe.setTime(40);
        assertEquals(40, recipe.getTime());

        // Verificamos el setter y getter de difficulty
        recipe.setDifficulty(3);
        assertEquals(3, recipe.getDifficulty());

        // Verificamos el setter y getter de diners
        recipe.setDiners(6);
        assertEquals(6, recipe.getDiners());

        // Verificamos el setter y getter de ingredients
        recipe.setIngredients("pasta, tomato sauce");
        assertEquals("pasta, tomato sauce", recipe.getIngredients());

        // Verificamos el setter y getter de description
        recipe.setDescription("A new description");
        assertEquals("A new description", recipe.getDescription());

        // Verificamos el setter y getter de elaboration
        recipe.setElaboration("Step 1: Boil pasta, Step 2: Cook sauce");
        assertEquals("Step 1: Boil pasta, Step 2: Cook sauce", recipe.getElaboration());

        // Verificamos el setter y getter de country
        recipe.setCountry("France");
        assertEquals("France", recipe.getCountry());

        // Verificamos el setter y getter de videoUrl
        recipe.setVideoUrl("new_video_url");
        assertEquals("new_video_url", recipe.getVideoUrl());
    }

    @Test
    void testAddComment() {
        // Crear un comentario
        Comment comment = new Comment("1", "Great recipe!", 3);

        // Añadir el comentario
        recipe.addComment(comment);

        // Verificar que el comentario se ha añadido correctamente
        assertEquals(1, recipe.getComments().size());
        assertEquals("Great recipe!", recipe.getComments().get(0).getText());
    }
}
