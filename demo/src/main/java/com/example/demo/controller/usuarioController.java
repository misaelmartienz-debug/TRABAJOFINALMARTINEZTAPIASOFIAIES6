package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller; // CAMBIADO a @Controller
import org.springframework.ui.Model; // IMPORTADO para pasar datos a la vista
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;

import java.util.List;
//import java.util.Optional; // Necesario para la búsqueda por ID

// Define que esta clase es un controlador MVC tradicional

@Controller
public class usuarioController {
    

private final UsuarioService Usuarioervice;

   
// Inyección de Dependencias por Constructor (Java 17 style)
   
     public void UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // --- MÉTODOS MVC ---

    // 1. Mostrar la lista de clientes activos (READ ALL - Vista Principal)
    // GET /clientes/
    @GetMapping("/listarClientes")
    public String listarClientesActivos(Model model) {
        // Obtenemos solo los clientes activos
        List<Cliente> clientes = clienteService.obtenerTodosClientesActivos();

        // Agregamos la lista al objeto Model para que la vista pueda acceder a ella
        model.addAttribute("clientes", clientes);

        // Retorna el nombre de la plantilla HTML a renderizar (ej: Thymeleaf o JSP)
        return "listaClientes";
    }

















}










