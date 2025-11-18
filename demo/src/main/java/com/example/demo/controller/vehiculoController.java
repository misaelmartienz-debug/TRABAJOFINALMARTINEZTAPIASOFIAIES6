package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Vehiculo;
import com.example.demo.service.VehiculoService;

import org.springframework.web.bind.annotation.*;


@Controller
public class vehiculoController {

    private final VehiculoService vehiculoService;

    public vehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    // 🟢 1) LISTAR VEHÍCULOS
    @GetMapping("/vehiculos")
    public ModelAndView listarVehiculo() {

        List<Vehiculo> vehiculos = vehiculoService.obtenerVehiculosActivos();

        ModelAndView carrito = new ModelAndView("listaVehiculo");
        carrito.addObject("vehiculos", vehiculos);

        return carrito;
    }

    // 🟣 2) DETALLE DE VEHÍCULO
    @GetMapping("/vehiculos/{id}")
    public ModelAndView verDetalleVehiculo(@PathVariable("id") Integer id) {

        Vehiculo vehiculo = vehiculoService.obtenerVehiculoPorId(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        ModelAndView mav = new ModelAndView("detalleVehiculo");
        mav.addObject("vehiculo", vehiculo);

        return mav;
    }

    // 🟡 3) FORMULARIO NUEVO VEHÍCULO
    @GetMapping("/vehiculo/nuevo")
    public ModelAndView mostrarFormularioVehiculo() {

        ModelAndView carrito = new ModelAndView("formVehiculo");
        carrito.addObject("vehiculo", new Vehiculo());

        return carrito;
    }

    // 🔵 4) GUARDAR VEHÍCULO
    @PostMapping("/vehiculo/guardar")
    public String guardarVehiculo(@ModelAttribute Vehiculo vehiculo) {

        vehiculoService.guardarVehiculo(vehiculo);

        return "redirect:/vehiculos";
    }
     //🔴 4) ELIMINAR VEHICULO (BORRADO LÓGICO)
    @GetMapping("/vehiculos/eliminar/{id}")
    public String eliminarVehiculo(@PathVariable("id") Integer id) {

    vehiculoService.eliminarVehiculo(id);

    return "redirect:/vehiculos";
}
// 🟤 5) FORMULARIO PARA EDITAR VEHÍCULO
@GetMapping("/vehiculo/editar/{id}")
public ModelAndView formularioEditarVehiculo(@PathVariable("id") Integer id) {

    // 1️⃣ Buscamos el vehículo por ID
    Vehiculo vehiculo = vehiculoService.obtenerVehiculoPorId(id)
            .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

    // 2️⃣ Creamos el ModelAndView y le decimos qué vista usar
    ModelAndView carrito = new ModelAndView("formVehiculo");

    
    carrito.addObject("vehiculo", vehiculo);

    // 4️⃣ Devolvemos el ModelAndView
    return carrito;
}
// 
// 🟣 6) PROCESAR ACTUALIZACIÓN
@PostMapping("/vehiculos/actualizar/{id}")
public String actualizarVehiculo(@PathVariable("id") Integer id,
                                 @ModelAttribute Vehiculo vehiculoActualizado) {

    // 🔥 Este es el correcto: asigna el ID verdadero
    vehiculoActualizado.setIdVehiculo(id);

    vehiculoService.actualizarVehiculo(id, vehiculoActualizado);

    return "redirect:/vehiculos";
}

}
