package com.recipes.recipes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.recipes.recipes.model.Comment;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    private Comment comment;

    @BeforeEach
    void setUp() {
        // Inicializamos el objeto Comment antes de cada prueba
        comment = new Comment("John Doe", "Great recipe!", 5);
    }

    @Test
    void testGettersAndSetters() {
        // Test para verificar los setters y getters de todos los campos

        // Verificamos el setter y getter de author
        comment.setAuthor("Jane Doe");
        assertEquals("Jane Doe", comment.getAuthor());

        // Verificamos el setter y getter de text
        comment.setText("Amazing recipe!");
        assertEquals("Amazing recipe!", comment.getText());

        // Verificamos el setter y getter de rating
        comment.setRating(4);
        assertEquals(4, comment.getRating());
    }
}
