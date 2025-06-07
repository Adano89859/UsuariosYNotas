package com.UsuariosYNotas.UsuarioYNotas.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class passService {

    private final PasswordEncoder passwordEncoder;

    public passService(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    public String hashPassword(String normalPassword){
        return passwordEncoder.encode(normalPassword);
    }

    public boolean verificarUsuario(String normalPassword, String hashedPassword){
        return passwordEncoder.matches(normalPassword, hashedPassword);
    }
}
