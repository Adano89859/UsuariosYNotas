package com.UsuariosYNotas.UsuarioYNotas.service;

import java.util.List;
import java.util.Optional;

//Será una interfaz "general" que aplicaremos en nuestro sistema cuando no se requieran especificaciones
public interface CrudService_Repository<T, Long> {

    //GET Todos
    List<T> getAll();

    //GET por Id
    Optional<T> getById(Long id);

    //POST
    T save(T entidad);

    //PUT
    T update(Long id, T entidad);

    //DELETE
    void deleteById(Long id);

}
