package com.UsuariosYNotas.UsuarioYNotas.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {
    
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

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