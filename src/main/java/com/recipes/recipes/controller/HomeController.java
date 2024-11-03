package com.recipes.recipes.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.recipes.recipes.security.TokenStore;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Controller
public class HomeController {
  private TokenStore tokenStore;

  public HomeController(TokenStore tokenStore) {
    super();
    this.tokenStore = tokenStore;
  }

  @GetMapping("/")
  public String root() {
    return "Home";
  }

  @GetMapping("/recetas")
  public String home() {
    return "Home";
  }

  @GetMapping("/greetings")
  public String getMethodName(@RequestParam(name="name", required=false, defaultValue = "Grupo 14") String name, Model model) {
    String url = "http://localhost:8081/greetings";

    final var restTemplate = new RestTemplate();
    String token = tokenStore.getToken();

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", token);

    HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("name", name);

    ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

    model.addAttribute("name", response.getBody());
    return "Greetings";
  }
  
}
