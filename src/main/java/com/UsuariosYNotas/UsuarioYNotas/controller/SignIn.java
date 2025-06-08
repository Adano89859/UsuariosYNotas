package com.UsuariosYNotas.UsuarioYNotas.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Esta clase implementa un login que se nutre de las credenciales introducidas por el usuario,
 * luego se guardan en el controller donde se verifica si el usuario existe o no o si las credenciales
 * introducidas son correctas usando la lógica implementada en los repositorios y servicios.
 */
public class SignIn {

    //ATRIBUTOS Y VALIDACIONES
    private String nombre;

    @NotBlank(message = "El email no puede quedar vacío")
    @Email(message = "El email debe tener un formato válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;
    
    //GETTERS Y SETTERS

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

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
