package com.recipes.recipes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {
	@PostMapping("/add-comment")
	public String addComment(@RequestParam("username") String username, 
														@RequestParam("comment") String comment,
														Model model) {
		System.out.println("Usuario: " + username);
		System.out.println("Comentario: " + comment);

		return "redirect:/recipes";
	}
}