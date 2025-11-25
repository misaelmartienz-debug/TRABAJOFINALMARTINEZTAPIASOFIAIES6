package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

    @GetMapping("/")
    public String inicio() {
        return "index"; 
    }

    // Si intenta ir a "Viajes" sin usuario → lo mando a registrar usuario
    @GetMapping("/viajes")
    public String viajes() {
        return "redirect:/usuarios/nuevo";
    }

    @GetMapping("/viaje/nuevo")
    public String nuevoViaje() {
        return "redirect:/usuarios/nuevo";
    }
}
