package com.UsuariosYNotas.UsuarioYNotas.controller;

import com.UsuariosYNotas.UsuarioYNotas.model.Usuario;
import com.UsuariosYNotas.UsuarioYNotas.service.UsuarioService;
import com.UsuariosYNotas.UsuarioYNotas.service.passService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v2")
public class UsuarioControllerV2 {

    private final UsuarioService usuarioService;
    private final passService pService;

    public UsuarioControllerV2(UsuarioService usuarioService, passService pService){
        this.usuarioService = usuarioService;
        this.pService = pService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@Valid @RequestBody SignIn signIn) {
        Optional<Usuario> usuarioOptional = usuarioService.findByEmail(signIn.getemail());

        if (usuarioOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encuentra el usuario seleccionado");
        }

        Usuario usuario = usuarioOptional.get();

        boolean esIgual = pService.verificarUsuario(signIn.getPassword(), usuario.getpasswordHash());

        if (!esIgual) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario o contraseña incorrectos");
        }

        return ResponseEntity.ok("Usuario logeado exitosamente");
    }
    
}