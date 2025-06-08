package com.UsuariosYNotas.UsuarioYNotas.repository;

import com.UsuariosYNotas.UsuarioYNotas.model.Nota;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    //MÉTODO PARA BUSCAR USUARIO POR ID
    List<Nota> findByUsuarioId(Long usuarioID, Sort sort);
}
