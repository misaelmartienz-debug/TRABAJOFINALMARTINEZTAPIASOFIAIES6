package com.example.demo.repository;


import com.example.demo.model.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConductorRepository1 extends JpaRepository<Conductor, Integer> {

    
     /*  Lista de conductores activos.*/
    List<Conductor> findByEstadoTrue();
    
}