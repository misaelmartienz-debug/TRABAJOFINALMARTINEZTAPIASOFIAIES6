package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Vehiculo;
import com.example.demo.repository.VehiculoRepository1;

@Service
public class VehiculoService {
 
@Autowired

private VehiculoRepository1 vehiculoRepository;

 
//1Guardar 
public Vehiculo guardarVehiculo (Vehiculo vehiculo) {
    return vehiculoRepository.save(vehiculo) ;
}



//Leer Vista de Vehiculos
public List<Vehiculo> obtenerVehiculosActivos (){
    return vehiculoRepository.findByEstadoTrue();
}


//Leer por Id Vehiculos

public Optional<Vehiculo> obtenerVehiculoPorId (Integer idVehiculo) {
    return vehiculoRepository.findById(idVehiculo);
}
//Actualizar si existe el vehiculo, map es parte de un condicional en el cual si existe o encontro por Id el vehiculo
//Una nueva funcion vehiculoExistente donde va a tener a remplazar SET en datos GET del Vehiculo ya encontrado
public Vehiculo actualizarVehiculo(Integer idVehiculo, Vehiculo detallesVehiculo) {
    return vehiculoRepository.findById(idVehiculo).map(vehiculoExistente -> {

        vehiculoExistente.setTipoVehiculo(detallesVehiculo.getTipoVehiculo());
        vehiculoExistente.setPatente(detallesVehiculo.getPatente());
        vehiculoExistente.setMarca(detallesVehiculo.getMarca());
        vehiculoExistente.setModelo(detallesVehiculo.getModelo());
        vehiculoExistente.setColor(detallesVehiculo.getColor());
        vehiculoExistente.setAnio(detallesVehiculo.getAnio());

        // 🔵 ESTA ES LA LÍNEA QUE FALTABA
        vehiculoExistente.setConductor(detallesVehiculo.getConductor());

        return vehiculoRepository.save(vehiculoExistente);
    }).orElse(null);
}


//Eliminar Vehiculo (Borrado Logico)
public boolean eliminarVehiculo (Integer idVehiculo) {
    Optional<Vehiculo> vehiculoEncontrado = vehiculoRepository.findById(idVehiculo);
    if (vehiculoEncontrado.isPresent()) {
        Vehiculo vehiculo = vehiculoEncontrado.get();
        vehiculo.setEstado(false);
        vehiculoRepository.save(vehiculo);
        return true;
    }
    return false;

}
}


  







