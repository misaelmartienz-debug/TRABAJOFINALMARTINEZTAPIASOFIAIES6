package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Vehiculo {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer idVehiculo;

@Enumerated(EnumType.STRING)
private TipoVehiculo tipoVehiculo;

@Column
private String patente;

@Column(nullable = false)
private String marca;

@Column(nullable = false)
private String modelo;

@Column 
private String color;

@Column (nullable = false)
 private Integer anio;


@OneToOne
    @JoinColumn(name = "conductor_id") // FOreing KEy
    private Conductor conductor;

@Column
 private boolean estado= true;


//Constructores//
public Vehiculo (){

}


//Parametrizado//


public Vehiculo(TipoVehiculo tipoVehiculo, String patente, String marca, String modelo, String color,
        Integer anio, Conductor conductor) {
    
    this.tipoVehiculo = tipoVehiculo;
    this.patente = patente;
    this.marca = marca;
    this.modelo = modelo;
    this.color = color;
    this.anio = anio;
    this.conductor = conductor;
    this.estado = true ;
}




//Getter y Setters//


public Integer getIdVehiculo() {
    return idVehiculo;
}


public void setIdVehiculo(Integer idVehiculo) {
    this.idVehiculo = idVehiculo;
}


public TipoVehiculo getTipoVehiculo() {
    return tipoVehiculo;
}

public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
    this.tipoVehiculo = tipoVehiculo;
}




public String getPatente() {
    return patente;
}


public void setPatente(String patente) {
    this.patente = patente;
}


public String getMarca() {
    return marca;
}


public void setMarca(String marca) {
    this.marca = marca;
}


public String getModelo() {
    return modelo;
}


public void setModelo(String modelo) {
    this.modelo = modelo;
}


public String getColor() {
    return color;
}


public void setColor(String color) {
    this.color = color;
}


public Integer getAnio() {
    return anio;
}


public void setAnio(Integer anio) {
    this.anio = anio;
}


public Conductor getConductor() {
    return conductor;
}


public void setConductor(Conductor conductor) {
    this.conductor = conductor;
}


public boolean isEstado() {
    return estado;
}


public void setEstado(boolean estado) {
    this.estado = estado;
}
}