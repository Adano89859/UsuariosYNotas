package com.UsuariosYNotas.UsuarioYNotas.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignIn {

    //ATRIBUTOS Y VALIDACIONES
    @NotBlank(message = "Debe proporcionar un nombre de usuario")
    private String nombre;

    @NotBlank(message = "El email no puede quedar vacío")
    @Email(message = "El email debe tener un formato válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;
    
    //GETTERS Y SETTERS

    public String getnombre(){
        return nombre;
    }

    public void setnombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getemail(){
        return email;
    }

    public void setemail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
