package com.recipes.recipes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.recipes.recipes.security.TokenStore;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TokenStoreTest {

    private TokenStore tokenStore;

    @BeforeEach
    void setUp() {
        // Inicializamos el TokenStore antes de cada prueba
        tokenStore = new TokenStore();
    }

    @Test
    void testGetSetToken() {
        // Configuramos un token de prueba
        String tokenValue = "testToken123";

        // Usamos el setter para asignar el token
        tokenStore.setToken(tokenValue);

        // Verificamos que el token asignado sea el mismo que se obtiene con el getter
        assertEquals(tokenValue, tokenStore.getToken(), "El token no coincide con el valor esperado.");
    }
}
