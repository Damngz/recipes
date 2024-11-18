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
        // Procesar el comentario
        System.out.println("Usuario: " + username);
        System.out.println("Comentario: " + comment);

        // Redirigir o actualizar el modelo si es necesario
        return "redirect:recipes"; // Cambia "/" por la URL que corresponda a tu página principal
    }
}