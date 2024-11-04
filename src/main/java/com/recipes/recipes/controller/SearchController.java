package com.recipes.recipes.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.recipes.recipes.model.Recipe;
import com.recipes.recipes.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/buscar")
public class SearchController {
  private final RecipeService recipeService;

  public SearchController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @GetMapping
  public String buscarRecetas(@RequestParam(value = "query", required = false) String query, Model model) {
    List<Recipe> recipes = recipeService.getAllRecipes();

    log.info(query);
    if (query != null && !query.trim().isEmpty()) {
      recipes = recipeService.searchRecipes(query);
    }

    model.addAttribute("recipes", recipes);
    model.addAttribute("query", query);
    return "search";
  }
}
