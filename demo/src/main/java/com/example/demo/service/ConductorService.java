package com.example.demo.service;



import com.example.demo.model.Conductor;
import com.example.demo.repository.ConductorRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Optional;

@Service
public class ConductorService {


    // Inyección de dependencias: permite usar los métodos del Repository
    @Autowired
    private ConductorRepository1 conductorRepository;

    // Métodos CRUD (5 métodos requeridos) 

    // 1. CREAR / GUARDAR (Create)
  
    public Conductor guardarConductor (Conductor conductor) {
        // La lógica podría ir aquí (ej: validar email antes de guardar)
        return conductorRepository.save(conductor);
    }
    
    // 2. "LEER TODOS' (Read All) - Filtrado por Borrado Lógico
    /**
     * Obtiene todos los conductores cuyo estado es TRUE (activos).
     * Usa el Query Method definido en el Repository.
     *  Lista de conductores activos.
     */
    public List<Conductor> obtenerTodosConductoresActivos() {
        return conductorRepository.findByEstadoTrue();
    }
    
    // 3. LEER POR ID (Read By ID)
    /**
     * Obtiene un conductor por su ID, independientemente de su estado (activo o inactivo).
     *  El ID del conductor a buscar.
     */
    public Optional<Conductor> obtenerConductorPorId(Integer ConductorId) {
        // Usamos findById que devuelve un Optional para manejar la posible ausencia del conductor.
        return conductorRepository.findById(ConductorId);
    }
    
    // 4. ACTUALIZAR (Update)
    /**
     * Actualiza la información de un conductor existente.
     * id El ID del conductor a actualizar.
     * detallesConductor Los nuevos datos del conductor.
     *  El conductor actualizado o null si no se encontró.
     */
    public Conductor actualizarConductor(Integer ConductorId, Conductor detallesConductor) {
        // 1. Busca el conductor existente
        return conductorRepository.findById(ConductorId).map(conductorExistente -> {



            // 2. Actualiza los campos (se asume que el ID ya está validado)
            conductorExistente.setNombre(detallesConductor.getNombre());
            conductorExistente.setApellido(detallesConductor.getApellido());
            conductorExistente.setCorreoElectronico(detallesConductor.getCorreoElectronico());
            conductorExistente.setTelefono(detallesConductor.getTelefono());
            conductorExistente.setDireccion(detallesConductor.getDireccion());
            
                       // 3. Guarda la entidad actualizada
            return conductorRepository.save(conductorExistente);
        }).orElse(null); // Devuelve Null
    }

    // 5. ELIMINAR (Delete) - Borrado Lógico
    /**
     * Realiza un borrado lógico, cambiando el atributo 'estado' a FALSE.
     *  El ID del conductor desactivar.
     *  true si la eliminación lógica fue exitosa, false si el conductor no fue encontrado.
     */
    public boolean eliminarConductorLogico(Integer conductorId) {
        Optional<Conductor> conductorEncontrado = conductorRepository.findById(conductorId);    
        if (conductorEncontrado.isPresent()) {
            Conductor conductor = conductorEncontrado.get();
            conductor.setEstado(false); // 🔑 Lógica clave: Borrado Lógico
            conductorRepository.save(conductor); // Persiste el cambio de estado
            return true;
        }
        return false; //Encontrado para eliminar
    }
}

