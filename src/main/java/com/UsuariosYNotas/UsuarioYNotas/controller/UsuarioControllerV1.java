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

import jakarta.validation.constraints.Positive;
import jakarta.validation.Valid;

import com.UsuariosYNotas.UsuarioYNotas.model.Usuario;
import com.UsuariosYNotas.UsuarioYNotas.service.UsuarioService;

import java.util.List;

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

    @GetMapping
    public List<Usuario> getUsuarios(){return usuarioService.listAll();}

    @GetMapping("/{id}")
    @ResponseBody
    public void getUsuarioId(@PathVariable("id") Long id) {
        usuarioService.findById(id);
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody @Valid Usuario usuario){
        return usuarioService.save(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> UpdateUsuario(@PathVariable Long id, @RequestBody @Valid Usuario nuevoUsuario){
        try {
            Usuario usuarioNuevo = usuarioService.update(id, nuevoUsuario);
            return ResponseEntity.ok(usuarioNuevo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable("id") Long id){
        usuarioService.deleteById(id);
    }
    
}
