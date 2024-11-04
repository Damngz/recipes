package com.recipes.recipes.model;

public class Recipe {
  private Long recipeId;
  private String title;
  private String imageUrl;
  private int time;
  private int difficulty;
  private int diners;
  private String ingredients;
  private String description;
  private String elaboration;
  private String country;
  
  public Recipe(Long recipeId, String title, String imageUrl, int time, int difficulty, int diners, String ingredients, String description, String elaboration, String country) {
    this.recipeId = recipeId;
    this.title = title;
    this.imageUrl = imageUrl;
    this.time = time;
    this.difficulty = difficulty;
    this.diners = diners;
    this.ingredients = ingredients;
    this.description = description;
    this.elaboration = elaboration;
    this.country = country;
  }

  public void setRecipeId(Long recipeId) {
    this.recipeId = recipeId;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }

  public void setDiners(int diners) {
    this.diners = diners;
  }

  public void setIngredients(String ingredients) {
    this.ingredients = ingredients;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setElaboration(String elaboration) {
    this.elaboration = elaboration;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Long getRecipeId() {
    return recipeId;
  }

  public String getTitle() {
    return title;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public int getTime() {
    return time;
  }

  public int getDifficulty() {
    return difficulty;
  }

  public int getDiners() {
    return diners;
  }

  public String getIngredients() {
    return ingredients;
  }

  public String getDescription() {
    return description;
  }

  public String getElaboration() {
    return elaboration;
  }

  public String getCountry() {
    return country;
  }
}
