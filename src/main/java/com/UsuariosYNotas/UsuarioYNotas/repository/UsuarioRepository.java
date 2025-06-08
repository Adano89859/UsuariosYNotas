package com.UsuariosYNotas.UsuarioYNotas.repository;

import com.UsuariosYNotas.UsuarioYNotas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //MÉTODO PARA BUSCAR POR EMAIL (Log-In)
    Optional<Usuario> findByEmail(String email);
}
