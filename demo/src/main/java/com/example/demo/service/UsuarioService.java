package com.example.demo.service;



import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Optional;

// Indica a Spring que esta clase es un componente de servicio
@Service
public class UsuarioService {

    // Inyección de dependencias: permite usar los métodos del Repository
    @Autowired
    private UsuarioRepository1 usuarioRepository;

    // Métodos CRUD (5 métodos requeridos) 

    // 1. CREAR / GUARDAR (Create)

    public Usuario guardarUsuario (Usuario usuario) {
        // La lógica podría ir aquí (ej: validar email antes de guardar)
        return usuarioRepository.save(usuario);
    }
    
    // 2. "LEER TODOS' (Read All) - Filtrado por Borrado Lógico
    public List<Usuario> obtenerTodosUsuarioActivos() {
    return usuarioRepository.findByEstadoTrue();
}

    // 3. LEER POR ID (Read By ID)
    public Optional<Usuario> obtenerUsuarioPorId(Integer UsuarioId) {
        // Usamos findById
        return usuarioRepository.findById(UsuarioId);
    }
    
    // 4. ACTUALIZAR (Update)

    
    public Usuario actualizarUsuario(Integer UsuarioId, Usuario detallesUsuario) {
        // 1. Buscar
        return usuarioRepository.findById(UsuarioId).map(usuarioExistente -> {



            // 2. Actualiza los campos (se asume que el ID ya está validado)
            usuarioExistente.setNombre(detallesUsuario.getNombre());
            usuarioExistente.setApellido(detallesUsuario.getApellido());
            usuarioExistente.setEmail(detallesUsuario.getEmail());
            usuarioExistente.setTelefono(detallesUsuario.getTelefono());
            usuarioExistente.setDireccion(detallesUsuario.getDireccion());
            
            // Nota: Podrías optar por no actualizar el estado aquí, o dejar que la lógica de soft-delete lo maneje.
    
            // 3. Guarda la entidad actualizada
            return usuarioRepository.save(usuarioExistente);
        }).orElse(null); // Devuelve null
    }

    // 5. ELIMINAR (Delete) - Borrado Lógico
    
    public boolean eliminarUsuarioLogico(Integer usuarioId) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(usuarioId);    
        if (usuarioEncontrado.isPresent()) {
            Usuario usuario = usuarioEncontrado.get();
            usuario.setEstado(false); // 🔑 Lógica clave: Borrado Lógico
            usuarioRepository.save(usuario); // Persiste el cambio de estado
            return true;
        }
        return false;
    }
}