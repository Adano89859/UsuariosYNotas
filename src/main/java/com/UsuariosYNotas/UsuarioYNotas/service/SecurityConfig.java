package com.UsuariosYNotas.UsuarioYNotas.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Configuración para implementar el hashing 
@Configuration
public class SecurityConfig {

    /**
     * Creamos una variable llamada passwordEncoder que se encargará de hashear
     * nuestra contraseña que se encuentra en texto plano por defecto.
     * 
     * Usamos una librería llamada BCryptPasswordEncoder que se encargará de convertir 
     * nuestra contraseña en texto plano en un texto hasheado.
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
