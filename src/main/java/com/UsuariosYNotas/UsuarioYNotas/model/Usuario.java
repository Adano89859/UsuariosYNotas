package com.UsuariosYNotas.UsuarioYNotas.model;

import java.util.List;

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

    @NotBlank(message = "Debe proporcionar un nombre de usuario")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank(message = "El email no puede quedar vacío")
    @Email(message = "El email debe tener un formato válido")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Column(name = "contrasena", nullable = false)
    private String passwordHash;

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

    public String getpasswordHash(){
        return passwordHash;
    }

    public void setpasswordHash(String passwordHash){
        this.passwordHash = passwordHash;
    }
}