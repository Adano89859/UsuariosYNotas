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

    //ATRIBUTOS
    private final UsuarioService usuarioService;

    //CONSTRUCTOR
    public UsuarioControllerV2(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    /**
     * Método para crear un usuario inicial, se recogen las credenciales introducidas por el usuario
     * en el sign-in, se recogen las validaciones y la lógica de los servicios y repositorios y se
     * valida al usuario una vez haya sido ingresado en la API.
     */
    @PostMapping("/sign-in")
    public ResponseEntity<Usuario> signIn(@Valid @RequestBody SignIn signIn) {
        // creamos un nuevo usuario inicial
        Usuario usuario = new Usuario();
        // se recogen las credenciales ingresadas por el usuario
        usuario.setNombre(signIn.getNombre());
        usuario.setEmail(signIn.getEmail());
        usuario.setPasswordHash(signIn.getPassword());

        /**
         * LLamamos al los servicios para que verifiquen que el usuario que se va a registrar
         * es nuevo o ya está registrado, en ese caso validará que las credenciales ingresadas
         * se correspondan con algún usuario registrado.
         */
        Usuario creado = usuarioService.registrarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
    
}