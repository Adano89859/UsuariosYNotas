package com.UsuariosYNotas.UsuarioYNotas.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Servicio que se encargará de proporcionar el hashing y validar a los usuarios en el Log-in
 * Este servicio se nutre de la configuración SecurityConfig y se aplica en UsuarioServiceImpl
 * y en UsuarioControllerV2 cuando estos dos intenten validar qué usuario se está logeando,
 * se llama a los métodos de este servicio, uno para hashear las credenciales a la hora de 
 * registrarse (servicio) y otro para validar al mismo usuario conforme a sus credenciales 
 * "email y contraseña" que se recogerán en el controller.
 */
@Service
public class passService {

    //ATRIBUTOS
    private final PasswordEncoder passwordEncoder;

    //CONSTRUCTOR
    public passService(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    //Método que convertirá nuestra contraseña normal en un hash
    public String hashPassword(String normalPassword){
        return passwordEncoder.encode(normalPassword);
    }

    //Método que verifica el usuario por sus credenciales (contraseñas)
    public boolean verificarUsuario(String normalPassword, String hashedPassword){
        return passwordEncoder.matches(normalPassword, hashedPassword);
    }
}
