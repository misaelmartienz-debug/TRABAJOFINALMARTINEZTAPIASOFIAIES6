package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Vehiculo {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer idVehiculo;

@Column
private String tipoVehiculo;

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


@ManyToOne
    @JoinColumn(name = "conductor_id") // FOreing KEy
    private Conductor conductor;

@Column
 private boolean estado;


//Constructores//
public Vehiculo (){

}


//Parametrizado//


public Vehiculo(Integer idVehiculo, String tipoVehiculo, String patente, String marca, String modelo, String color,
        Integer anio, Conductor conductor) {
    this.idVehiculo = idVehiculo;
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


public String getTipo() {
    return tipoVehiculo;
}


public void setTipo(String tipo) {
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