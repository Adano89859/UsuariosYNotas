package com.UsuariosYNotas.UsuarioYNotas.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Usuario")
public class Usuario {
    
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no pued estar vacío")
    @Size(max = 100, message = "El nombre debe tener como máximo 100 caracteres")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank(message = "El email no debe estar vacío")
    @Email(message = "Introduzca un email válido")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Debe haber una contraseña")
    @Size(min = 8, message = "La contraseña debe tenr almenos 8 caracteres")
    @Column(name = "contrasena", nullable = false)
    private String passwordHash;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Nota> notas;

    //Métodos

    public Usuario(){}

    public Usuario(Long id, String nombre, String email, String passwordHash, List<Nota> notas ){
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.passwordHash = passwordHash;
        this.notas = notas;

    }

    //GETTERS Y SETTERS

    public List<Nota> getNotas(){
        return notas;
    }

    public void setNotas(List<Nota> notas){
        this.notas = notas;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPasswordHash(){
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash){
        this.passwordHash = passwordHash;
    }
}