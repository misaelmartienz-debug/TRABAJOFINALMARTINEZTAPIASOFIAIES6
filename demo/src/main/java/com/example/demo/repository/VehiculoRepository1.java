package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Vehiculo;

public interface VehiculoRepository1 extends JpaRepository  <Vehiculo, Integer> {


List<Vehiculo> findByEstadoTrue();
    
}
