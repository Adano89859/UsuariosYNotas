package com.UsuariosYNotas.UsuarioYNotas.repository;

import java.util.List;
import java.util.Optional;

//Será una interfaz "general" que aplicaremos en nuestro sistema cuando no se requieran especificaciones
public interface CrudService_Repository<Entidad, Long> {

    //GET Todos
    List<Entidad> getAll();

    //GET por Id
    Optional<Entidad> getById(Long id);

    //POST
    Entidad save(Entidad entidad);

    //PUT
    Entidad update(Long id, Entidad entidad);

    //DELETE
    void deleteById(Long id);

}
