package com.example.demo.model;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;



@Entity

public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioId; // Primary Key

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private Integer telefono;

    @Column
    private String direccion;

    // Relación 1:N con Compras: Un Cliente hace muchas Compras.
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Viaje> ViajesRealizados = new ArrayList<>();

    // Atributo de Borrado Lógico con EL estado del modelo
    @Column(nullable = false)
    private boolean estado = true;

    
    // Constructores, Getters y Setters...
    // Constructor Vacío
    public Usuario() {}

    // Constructor con parámetros
    public Usuario(String nombre, String apellido, String email, Integer telefono, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estado = true;
    }

       // Getters y Setters
    public Integer getUsuarioId() 
    { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }
   
   
   
    public String getNombre() 
    { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    
    public Integer getTelefono() { return telefono; }
    public void setTelefono(Integer telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public List<Viaje> getViajesRealizados() { return ViajesRealizados; }
    public void setViajesRealizadas(List<Viaje> viajesRealizados) { this.ViajesRealizados = viajesRealizados; }
    
    
    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }
}