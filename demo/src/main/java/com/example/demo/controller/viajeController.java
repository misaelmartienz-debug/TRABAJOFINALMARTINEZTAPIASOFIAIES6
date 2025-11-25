package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.*;
import com.example.demo.service.VehiculoService;
import com.example.demo.service.ViajeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class viajeController {

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private ViajeService viajeService;


    // PASO 1: Mostrar tipo de viaje
    @GetMapping("/viajes/inicio")
    public ModelAndView mostrarTiposDeViaje(HttpSession session) {

        if (session.getAttribute("usuarioLogueado") == null) {
            return new ModelAndView("redirect:/usuarios/nuevo");
        }

        return new ModelAndView("tipoViaje");
    }


    //PASO 2: Elegir vehículo (recibe tipoViaje)
    @GetMapping("/viajes/seleccionar")
    public ModelAndView seleccionarVehiculo(
            @RequestParam TipoViaje tipoViaje,
            HttpSession session) {

        if (session.getAttribute("usuarioLogueado") == null) {
            return new ModelAndView("redirect:/usuarios/nuevo");
        }

        // Guardamos el tipo de viaje en SESIÓN
        session.setAttribute("tipoViajeSeleccionado", tipoViaje);

        List<Vehiculo> vehiculos = vehiculoService.obtenerVehiculosActivos();

        ModelAndView carrito = new ModelAndView("selecViajeVehiculo");
        carrito.addObject("vehiculos", vehiculos);
        carrito.addObject("tipoViaje", tipoViaje);

        return carrito;
    }


    //PASO 3: Registrar el viaje
    @PostMapping("/viajes/registrar")
    public ModelAndView registrarViaje(
            @RequestParam Integer vehiculoId,
            HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        TipoViaje tipoViaje = (TipoViaje) session.getAttribute("tipoViajeSeleccionado");

        Vehiculo vehiculo = vehiculoService.obtenerVehiculoPorId(vehiculoId)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        Viaje viaje = viajeService.registrarViaje(usuario, vehiculo, tipoViaje);

        ModelAndView carrito = new ModelAndView("viajeDetalle");
        carrito.addObject("viaje", viaje);

        return carrito;
    }

}
