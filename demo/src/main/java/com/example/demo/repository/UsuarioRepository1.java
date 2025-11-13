package com.example.demo.repository;


import com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository1 extends JpaRepository<Usuario, Integer> {

    /**
     * Obtiene una lista de Clientes cuyo atributo 'estado' es TRUE.
     * Utiliza el Query Method de JPA: findBy + NombreAtributo + True/False
     *  Lista de clientes activos.
     */
    List<Usuario> findByEstadoTrue();
    
}