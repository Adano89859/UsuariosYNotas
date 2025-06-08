package com.UsuariosYNotas.UsuarioYNotas.service;

import com.UsuariosYNotas.UsuarioYNotas.model.Usuario;
import com.UsuariosYNotas.UsuarioYNotas.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz que aplicará las peticiones del controller en base a la lógica implementada
 * en la implementación del servicio del usuario (UsuarioServiceImpl)
*/
public interface UsuarioService extends CrudService_Repository<Usuario, Long> {

    // Métodos de petición Servicio-Controlador
    public List<Usuario> listAll();

    public Usuario save(Usuario usuario);

    public Optional<Usuario> findById(Long id);

    public Optional<Usuario> findByEmail(String email);

    public void deleteById(Long id);

    public Usuario registrarUsuario(Usuario usuario);

    public Usuario update(Long id, Usuario usuario);
}
