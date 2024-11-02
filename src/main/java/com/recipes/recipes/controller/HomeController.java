package com.recipes.recipes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  @GetMapping("/")
  public String root() {
    return "Home";
  }

  @GetMapping("/recetas")
  public String home() {
    return "Home";
  }

}
