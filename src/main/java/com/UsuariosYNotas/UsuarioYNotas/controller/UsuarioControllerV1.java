package com.UsuariosYNotas.UsuarioYNotas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.UsuariosYNotas.UsuarioYNotas.model.Usuario;
import com.UsuariosYNotas.UsuarioYNotas.service.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/usuarios")
@Validated
public class UsuarioControllerV1 {

    //ATRIBUTOS
    private final UsuarioService usuarioService;

    //CONSTRUCTOR
    public UsuarioControllerV1(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    //LISTAR USUARIOS
    @GetMapping
    public List<Usuario> getUsuarios(){return usuarioService.listAll();}

    //ENCONTRAR USUARIOS POR ID
    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Usuario> getUsuarioId(@PathVariable("id") Long id) {
         return usuarioService.findById(id);
    }

    //CREAR USUARIO
    @PostMapping
    public Usuario crearUsuario(@RequestBody @Valid Usuario usuario){
        return usuarioService.save(usuario);
    }

    //ACTUALIZAR USUARIO
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody @Valid Usuario nuevoUsuario){
        try {
            Usuario usuarioNuevo = usuarioService.update(id, nuevoUsuario);
            return ResponseEntity.ok(usuarioNuevo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //ELIMINAR USUARIO POR ID
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable("id") Long id){
        usuarioService.deleteById(id);
    }
    
}
