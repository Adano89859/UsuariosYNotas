package com.UsuariosYNotas.UsuarioYNotas.controller;

import com.UsuariosYNotas.UsuarioYNotas.model.Usuario;
import com.UsuariosYNotas.UsuarioYNotas.service.UsuarioService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v2")
public class UsuarioControllerV2 {

    private final UsuarioService usuarioService;

    public UsuarioControllerV2(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Usuario> signIn(@Valid @RequestBody SignIn signIn) {
        Usuario usuario = new Usuario();
        usuario.setNombre(signIn.getNombre());
        usuario.setEmail(signIn.getEmail());
        usuario.setPasswordHash(signIn.getPassword());

        Usuario creado = usuarioService.registrarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
    
}