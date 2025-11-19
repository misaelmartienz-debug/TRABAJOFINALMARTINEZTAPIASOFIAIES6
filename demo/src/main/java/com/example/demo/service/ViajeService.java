package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TipoViaje;
import com.example.demo.model.TipoVehiculo;
import com.example.demo.model.Usuario;
import com.example.demo.model.Vehiculo;
import com.example.demo.model.Viaje;
import com.example.demo.repository.ViajeRepository;

@Service
public class ViajeService {

    @Autowired
    private ViajeRepository viajeRepository;

    // 🔵 GUARDAR VIAJE
    public Viaje guardarViaje(Viaje viaje) {
        return viajeRepository.save(viaje);
    }

    // 🔵 BUSCAR VIAJE POR ID
    public Optional<Viaje> obtenerViajePorId(Integer id) {
        return viajeRepository.findById(id);
    }

    // 🔵 CALCULAR PRECIO SEGÚN TIPO DE VIAJE Y VEHÍCULO
    public double calcularPrecio(TipoViaje tipoViaje, TipoVehiculo tipoVehiculo) {

        double base = 0;

        // Precio base por distancia
        switch (tipoViaje) {
            case CORTA:
                base = 7000;
                break;
            case MEDIA:
                base = 10000;
                break;
            case LARGA:
                base = 20000;
                break;
        }

        // Aumento según tipo de vehículo
        double extra = 0;

        switch (tipoVehiculo) {
            case X:
                extra = 0;       // sin aumento
                break;
            case LUXE:
                extra = 0.10;    // +10%
                break;
            case PREMIUM:
                extra = 0.20;    // +20%
                break;
        }

        return base + (base * extra);
    }

    // 🔵 REGISTRAR VIAJE COMPLETO
    public Viaje registrarViaje(Usuario usuario, Vehiculo vehiculo, TipoViaje tipoViaje) {

        double precioFinal = calcularPrecio(tipoViaje, vehiculo.getTipoVehiculo());

        Viaje viaje = new Viaje();
        viaje.setUsuario(usuario);
        viaje.setVehiculo(vehiculo);
        viaje.setTipoViaje(tipoViaje);
        viaje.setFechaViaje(LocalDateTime.now());
        viaje.setPrecio(precioFinal);
        viaje.setEstado(true);

        return viajeRepository.save(viaje);
    }

}



