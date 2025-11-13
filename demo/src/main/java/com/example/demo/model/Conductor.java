package com.example.demo.model;

import jakarta.persistence.*;

@Entity

public class Conductor {
@Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer conductorId;
@Column
 private String nombre;

@Column
 private String apellido;
@Column
 private Integer telefono;
@Column
private String  correoElectronico;
@Column
private Integer edad;
@Column
private String direccion; 
@Column
private boolean estado;


// Constructor por defecto//
public Conductor() {}

// Contructor con Parametros//
public Conductor (String nombre, String apellido, Integer telefono, String correoElectronico, Integer edad, String dirección){
 this.nombre= nombre;
 this.apellido=apellido;
 this.telefono=telefono;
 this.correoElectronico=correoElectronico;
 this.edad=edad;
 this.direccion=direccion;
 this.estado= true;
}








//getter y setters//

public Integer getConductorId() {
    return conductorId;
}

public void setConductorId(Integer conductorId) {
    this.conductorId = conductorId;
}

public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}

public String getApellido() {
    return apellido;
}

public void setApellido(String apellido) {
    this.apellido = apellido;
}

public Integer getTelefono() {
    return telefono;
}

public void setTelefono(Integer telefono) {
    this.telefono = telefono;
}

public String getCorreoElectronico() {
    return correoElectronico;
}

public void setCorreoElectronico(String correoElectronico) {
    this.correoElectronico = correoElectronico;
}

public Integer getEdad() {
    return edad;
}

public void setEdad(Integer edad) {
    this.edad = edad;
}


public boolean isEstado() {
    return estado;
}

public void setEstado(boolean estado) {
    this.estado = estado;
}

public String getDireccion() {
    return direccion;
}

public void setDireccion(String direccion) {
    this.direccion = direccion;
}
























//@OneToMany // la clase del conductor tiene muchos autos,
    
}
