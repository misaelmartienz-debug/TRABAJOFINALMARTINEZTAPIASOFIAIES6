package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // 1) LISTAR USUARIOS
    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.obtenerTodosUsuarioActivos();
        model.addAttribute("usuarios", usuarios);
        return "listaUsuario";
    }

    // 2) FORMULARIO NUEVO USUARIO
    @GetMapping("/usuarios/nuevo")
    public String mostrarFormularioNuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "formUsuario";
    }

    // 3) GUARDAR USUARIO
    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario, HttpSession session) {

        usuarioService.guardarUsuario(usuario);
        session.setAttribute("usuarioLogueado", usuario);

        return "redirect:/usuarios";
    }

    // 4) ELIMINAR USUARIO (BORRADO LOGICO)
    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") Integer id) {
        usuarioService.eliminarUsuarioLogico(id);
        return "redirect:/usuarios";
    }

    // 5) FORMULARIO PARA EDITAR
    @GetMapping("/usuarios/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        model.addAttribute("usuario", usuario);
        return "formUsuario";
    }

    // 6) PROCESAR EDICIÓN
    @PostMapping("/usuarios/actualizar/{id}")
    public String actualizarUsuario(
            @PathVariable("id") Integer id,
            @ModelAttribute Usuario usuarioActualizado) {

        usuarioActualizado.setUsuarioId(id);
        usuarioService.actualizarUsuario(id, usuarioActualizado);

        return "redirect:/usuarios";
    }

    // 7) DETALLE
    @GetMapping("/usuarios/{id}")
    public String verDetalleUsuario(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        model.addAttribute("usuario", usuario);
        return "detalleUsuario";
    }
}
