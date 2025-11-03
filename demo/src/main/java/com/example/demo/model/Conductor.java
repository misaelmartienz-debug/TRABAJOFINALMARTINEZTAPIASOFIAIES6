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
private boolean estado;

// Constructor por defecto//
public Conductor() {}

// Contructor con Parametros//
public Conductor (String nombre, String apellido, Integer telefono, String correoElectronico, Integer edad){
 this.nombre= nombre;
 this.apellido=apellido;
 this.telefono=telefono;
 this.correoElectronico=correoElectronico;
 this.edad=edad;
 this.estado= true;
}

//getter y setters//

public Integer getConductorId() {return conductorId;}
public void  setCondutorId ( Integer conductorId) {this.conductorId=conductorId;}









//@OneToMany // la clase del conductor tiene muchos autos,
    
}
