package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Auto {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer idAuto;

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
public Auto (){

}


//Parametrizado//
public Auto (String patente, String marca, String modelo, String color, Integer anio, Conductor conductor ){
this.marca= marca;
this.modelo=modelo;
this.color=color;
this.anio=anio;
this.conductor=conductor;
this.estado=true;
}
//Getter y Setters//

public String getPatente (){
    return patente;
}
public void setPatente (String patente) {this.patente=patente;}

public String getMarca (){
return marca;
}
public void setMarca (String marca) {this.marca=marca;}

public String getColor (){
    return color;
}
public void setColor (String color) {this.color=color;}

public Integer getAnio () {
return anio;
}
public void setAnio (Integer anio) {this.anio=anio;}

public Conductor getConductor () {
    return conductor;
}
public void setCondutor (Conductor conductor ) {this.conductor=conductor;}

public boolean isEstado (){
    return estado;
}

public void setEstado (boolean estado) { this.estado=estado;}
}


















