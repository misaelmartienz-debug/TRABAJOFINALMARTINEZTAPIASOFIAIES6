package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Conductor;
import com.example.demo.service.ConductorService;

@Controller
public class conductorController {
    
    private final ConductorService conductorService;


    public conductorController(ConductorService conductorService) {
        this.conductorService= conductorService;
    }




    // 🟢 1) LISTAR CONDUCTORES
    @GetMapping("/conductores")  
    
    public String listarConductores(Model model) {

        // Pedimos al service la lista de conductores activos
        List<Conductor> conductores = conductorService.obtenerTodosConductoresActivos();

        // Metemos la lista en el modelo para mandarla a la vista
        model.addAttribute("conductores", conductores);

        // Nombre de la vista (archivo HTML en templates)
        return "listaConductor";
    }
     // 🟡2) FORMULARIO NUEVO CONDUCTOR 
    @GetMapping("/conductores/nuevo") 
    public String mostrarFormularioNuevoConductor(Model model) {

        // Enviamos un objeto vacío para que el formulario lo llene
        model.addAttribute("conductor", new Conductor());

        return "formConductor"; // vista del formulario
    }
    // 🔵 3) GUARDAR CONDUCTOR (CREATE)
   @PostMapping("/conductores/guardar")
    public String guardarConductor(@ModelAttribute Conductor conductor) {

        // Guardamos usando el service
        conductorService.guardarConductor(conductor);

        // Después de guardar, volvemos a la lista
        return "redirect:/conductores";

}
    //🔴 4) ELIMINAR CONDUCTOR (BORRADO LÓGICO)
    @GetMapping("/conductores/eliminar/{id}")
    public String eliminarConductor(@PathVariable("id") Integer id) {

        // Llamamos al service para eliminar (borrado lógico)
        conductorService.eliminarConductorLogico(id);

        // Volvemos a la lista
        return "redirect:/conductores";
    }
    // 🟤 5) FORMULARIO EDITAR CONDUCTOR
    @GetMapping("/conductores/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model) {
    Conductor conductor = conductorService.obtenerConductorPorId(id)
            .orElseThrow(() -> new RuntimeException("Conductor no encontrado"));
    model.addAttribute("conductor", conductor);
    return "formConductor";
}
    // 🟣 6) PROCESAR EDICIÓN
    @PostMapping("/conductores/editar/{id}")
    public String procesarEdicion(@PathVariable("id") Integer id, @ModelAttribute Conductor conductorActualizado) {
        // Llamamos al service para actualizar el conductor
        conductorService.actualizarConductor(id, conductorActualizado);
        // Volvemos a la lista de conductores
        return "redirect:/conductores";
    }
}