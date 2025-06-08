package com.UsuariosYNotas.UsuarioYNotas.service;

import com.UsuariosYNotas.UsuarioYNotas.service.UsuarioService;

import jakarta.transaction.Transactional;

import com.UsuariosYNotas.UsuarioYNotas.model.Usuario;
import com.UsuariosYNotas.UsuarioYNotas.repository.UsuarioRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioServiceImpl extends AbstractCrudService<Usuario> implements UsuarioService {

    //ATRIBUTOS
    private final UsuarioRepository usuarioRepository;
    //Revisar
    private final passService pService;

    //CONSTRUCTOR
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, passService pService){
        super(usuarioRepository);
        this.usuarioRepository = usuarioRepository;
        this.pService = pService;
    }

    //LISTAR
    @Override
    public List<Usuario> listAll(){
        return usuarioRepository.findAll();
    }
    //GUARDAR
    @Override
    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    //ENCONTRAR POR ID
    @Override
    public Optional<Usuario> findById(Long id){
        return usuarioRepository.findById(id);
    }
    //ENCONTRAR POR EMAIL (LOGIN)
    @Override
    public Optional<Usuario> findByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }
    //ELIMINAR POR ID
    @Override
    public void deleteById(Long id){
        usuarioRepository.deleteById(id);
    }
    
    //REGISTRAR USUARIO (CONTROLLER V2)
    @Override
    public Usuario registrarUsuario(Usuario usuario){
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe un usuario con este email");
        }

        String HashedPassword = pService.hashPassword(usuario.getPasswordHash());
        usuario.setPasswordHash(HashedPassword);

        if (usuario.getNotas() == null) {
            usuario.setNotas(new ArrayList<>());
        }

        return usuarioRepository.save(usuario);
    }

    //ACTUALIZAR USUARIO
    @Override
    public Usuario update(Long id, Usuario usuario){

        if (!usuarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el usuario con el ID: "+id);
        }

        Usuario existente = usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encuentra el usuario seleccionado"));
        BeanUtils.copyProperties(usuario, existente, "id");
        return usuarioRepository.save(existente);
    }
}