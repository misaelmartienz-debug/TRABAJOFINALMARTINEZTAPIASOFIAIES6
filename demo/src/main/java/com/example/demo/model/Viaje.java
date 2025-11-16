package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity
public class Viaje {
    
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer viajeId; // Primary Key

@ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false) 
    private Usuario usuario;


@ManyToOne 
    @JoinColumn(name = "vehiculo_id", nullable = false) // Ya NO es UNIQUE
    private Vehiculo vehiculo;

    @Column(nullable = false)
    private LocalDateTime fechaViaje; // Para fecha y hora

    @Column(nullable = false)
    private Double precio;

    // Atributo de Borrado Lógico
    @Column(nullable = false)
    private boolean estado = true;

    // Constructores, Getters y Setters...

 // Constructor Vacío
    public Viaje() {}

// Constructor con parámetros
    public Viaje(Usuario usuario , Vehiculo Vehiculo, LocalDateTime fechaCompra, Double precioFinal) {
        this.usuario = usuario;
        this.vehiculo = vehiculo;
        this.fechaViaje = fechaViaje;
        this.precio = precio;
        this.estado = true;
    }

      // Getters y Setters

    public Integer getViajeId() { return viajeId; }

    public void setViajeId(Integer viajeId) { this.viajeId = viajeId; }


    public Usuario getUsario() { return usuario; }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Vehiculo getVehiculo() { return vehiculo; } 
    public void setVehiculo( Vehiculo vehiculo) { this.vehiculo = vehiculo; }

    public LocalDateTime getFechaViaje() { return fechaViaje; }
    public void setFechaViaje (LocalDateTime fechaCompra) { this.fechaViaje = fechaViaje; }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }
}