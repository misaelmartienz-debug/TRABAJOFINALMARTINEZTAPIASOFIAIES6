package com.example.demo.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer viajeId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoViaje tipoViaje;

    @Column(nullable = false)
    private LocalDateTime fechaViaje;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private boolean estado = true;

    // Constructor vacío
    public Viaje() {
    }

    // Constructor con parámetros
    public Viaje(Integer viajeId, Usuario usuario, Vehiculo vehiculo, TipoViaje tipoViaje, LocalDateTime fechaViaje,
            Double precio, boolean estado) {
        this.viajeId = viajeId;
        this.usuario = usuario;
        this.vehiculo = vehiculo;
        this.tipoViaje = tipoViaje;
        this.fechaViaje = fechaViaje;
        this.precio = precio;
        this.estado = estado;
    }

    // Getters y setters

    public Integer getViajeId() {
        return viajeId;
    }

    public void setViajeId(Integer viajeId) {
        this.viajeId = viajeId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public TipoViaje getTipoViaje() {
        return tipoViaje;
    }

    public void setTipoViaje(TipoViaje tipoViaje) {
        this.tipoViaje = tipoViaje;
    }

    public LocalDateTime getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(LocalDateTime fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}