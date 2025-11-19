package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Vehiculo;
import com.example.demo.service.ConductorService;
import com.example.demo.service.VehiculoService;

@Controller
public class vehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private ConductorService conductorService;

    // 🟢 LISTAR
    @GetMapping("/vehiculos")
    public String listarVehiculos(Model model) {
        model.addAttribute("vehiculos", vehiculoService.obtenerVehiculosActivos());
        return "listaVehiculo";
    }

    // 🟡 NUEVO
    @GetMapping("/vehiculos/nuevo")
    public String mostrarFormularioNuevoVehiculo(Model model) {
        model.addAttribute("vehiculo", new Vehiculo());
        model.addAttribute("conductores", conductorService.obtenerTodosConductoresActivos());
        return "formVehiculo";
    }

    @PostMapping("/vehiculos/guardar")
    public String guardarVehiculo(@ModelAttribute Vehiculo vehiculo) {
        vehiculoService.guardarVehiculo(vehiculo);
        return "redirect:/vehiculos";
    }

    // 🟠 EDITAR
    @GetMapping("/vehiculos/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model) {

        Vehiculo vehiculo = vehiculoService.obtenerVehiculoPorId(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        model.addAttribute("vehiculo", vehiculo);
        model.addAttribute("conductores", conductorService.obtenerTodosConductoresActivos());

        return "formVehiculo";
    }

    @PostMapping("/vehiculos/editar/{id}")
    public String procesarEdicion(@PathVariable("id") Integer id,
                                  @ModelAttribute Vehiculo vehiculoActualizado) {

        vehiculoService.actualizarVehiculo(id, vehiculoActualizado);
        return "redirect:/vehiculos";
    }

    // 🔴 ELIMINAR
    @GetMapping("/vehiculos/eliminar/{id}")
    public String eliminarVehiculo(@PathVariable("id") Integer id) {
        vehiculoService.eliminarVehiculo(id);
        return "redirect:/vehiculos";
    }

    // 🔵 VER DETALLE
    @GetMapping("/vehiculo/{id}")
    public String detalleVehiculo(@PathVariable Integer id, Model model) {

        Vehiculo vehiculo = vehiculoService.obtenerVehiculoPorId(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        model.addAttribute("vehiculo", vehiculo);
        return "detalleVehiculo";
    }
}
