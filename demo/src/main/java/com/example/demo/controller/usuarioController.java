package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller; // CAMBIADO a @Controller
import org.springframework.ui.Model; // IMPORTADO para pasar datos a la vista
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;

import java.util.List;
// Necesario para la búsqueda por ID


@Controller
public class usuarioController {
    

private final UsuarioService Usuarioservice;

   
// Inyección de Dependencias por Constructor (Java 17 style)
  public usuarioController(UsuarioService usuarioService) {
    this.Usuarioservice = usuarioService;
}

    // --- MÉTODOS MVC ---

    @GetMapping("/listarUsuario")
    public String listarUsuarioActivos(Model model) {
        
        List<Usuario> usuario = UsuarioService.obtenerTodosUsuarioActivos();

        model.addAttribute("usuario", usuario);

        
        return "listaUsuario";
    }


}










