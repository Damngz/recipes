package com.recipes.recipes;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.recipes.recipes.service.RecipeService;

@SpringBootTest
class RecipesApplicationTests {

	@Autowired
	private RecipeService recipeService;

	@Test
	void contextLoads() {
		assertNotNull(recipeService, "El servicio RecipeService debería estar presente en el contexto");
	}

	@Test
	void testMainMethod() {
		RecipesApplication.main(new String[] {});
	}

}
