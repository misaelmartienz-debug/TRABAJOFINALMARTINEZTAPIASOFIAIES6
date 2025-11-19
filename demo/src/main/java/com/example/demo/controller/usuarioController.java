package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class usuarioController {

    private final UsuarioService usuarioService;

    // 🔹 Inyección del service por constructor
    public usuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


        // 🟢 1) LISTAR USUARIOS
    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {

        // Pedimos al service la lista de usuarios activos
        List<Usuario> usuarios = usuarioService.obtenerTodosUsuarioActivos();

        // Metemos la lista en el modelo para mandarla a la vista
        model.addAttribute("usuarios", usuarios);

        // Nombre de la vista (archivo HTML en templates)
        return "listaUsuario";
    }

    // 🟡 2) FORMULARIO NUEVO USUARIO
    @GetMapping("/usuarios/nuevo")
    public String mostrarFormularioNuevoUsuario(Model model) {

        // Enviamos un objeto vacío para que el formulario lo llene
        model.addAttribute("usuario", new Usuario());

        return "formUsuario"; // vista del formulario
    }
    // 🔵 3) GUARDAR USUARIO (CREATE)
   @PostMapping("/usuario/guardar")
public String guardarUsuario(@ModelAttribute Usuario usuario, HttpSession session) {

    usuarioService.guardarUsuario(usuario);

    // Guardamos el usuario en sesión para saber que ya está logueado
    session.setAttribute("usuarioLogueado", usuario);

    return "redirect:/";
}

        // Guardamos usando el service
        usuarioService.guardarUsuario(usuario);

        // Después de guardar, volvemos a la lista
        return "redirect:/usuarios";
        }
    


    //🔴 4) ELIMINAR USUARIO (BORRADO LÓGICO)
    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") Integer id) {

        usuarioService.eliminarUsuarioLogico(id);

        return "redirect:/usuarios";
    }

     // 🟤 5) FORMULARIO PARA EDITAR
    @GetMapping("/usuarios/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model) {

    //🔴 4) ELIMINAR USUARIO (BORRADO LÓGICO)
    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") Integer id) {

        usuarioService.eliminarUsuarioLogico(id);

        return "redirect:/usuarios";
    }

     // 🟤 5) FORMULARIO PARA EDITAR
    @GetMapping("/usuarios/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model) {

    Usuario usuario = usuarioService.obtenerUsuarioPorId(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    model.addAttribute("usuario", usuario);

    return "formUsuario"; 
}


    // 🟤 6) PROCESAR EDICIÓN
@PostMapping("/usuarios/actualizar/{id}")
public String actualizarUsuario(@PathVariable("id") Integer id, @ModelAttribute Usuario usuarioActualizado) {

    // Seteamos el ID al usuario actualizado
    usuarioActualizado.setUsuarioId(id);

    // Llamamos al service para actualizar los datos
    usuarioService.actualizarUsuario(id, usuarioActualizado);

    // Redirige a la lista de usuarios
    return "redirect:/usuarios";
}
// 🟣 7) DETALLE DE USUARIO
@GetMapping("/usuarios/{id}")
public String verDetalleUsuario(@PathVariable("id") Integer id, Model model) {

    Usuario usuario = usuarioService.obtenerUsuarioPorId(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    model.addAttribute("usuario", usuario);

    return "detalleUsuario";
}

}