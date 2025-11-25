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

    // Guardar un viaje
    public Viaje guardarViaje(Viaje viaje) {
        return viajeRepository.save(viaje);
    }

    // Buscar viaje por ID
    public Optional<Viaje> obtenerViajePorId(Integer id) {
        return viajeRepository.findById(id);
    }

    // Calcular precio según tipo de viaje y tipo de vehículo
    public double calcularPrecio(TipoViaje tipoViaje, TipoVehiculo tipoVehiculo) {

        double base = 0;

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

        double extra = 0;

        switch (tipoVehiculo) {
            case X:
                extra = 0;
                break;
            case LUXE:
                extra = 0.10;
                break;
            case PREMIUM:
                extra = 0.20;
                break;
        }

        return base + (base * extra);
    }

    // Registrar un viaje completo
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
