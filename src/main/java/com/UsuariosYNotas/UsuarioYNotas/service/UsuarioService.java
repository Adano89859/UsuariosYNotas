package com.UsuariosYNotas.UsuarioYNotas.service;

import com.UsuariosYNotas.UsuarioYNotas.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService extends CrudService_Repository<Usuario, Long> {

    public List<Usuario> listAll();

    public Usuario save(Usuario usuario);

    public Optional<Usuario> findById(Long id);

    public Optional<Usuario> findByEmail(String email);

    public void deleteById(Long id);

    public Usuario registrarUsuario(Usuario usuario);

    public Usuario update(Long id, Usuario usuario);
}
