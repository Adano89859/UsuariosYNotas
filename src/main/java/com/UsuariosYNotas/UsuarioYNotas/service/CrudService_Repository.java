package com.UsuariosYNotas.UsuarioYNotas.service;

import java.util.List;
import java.util.Optional;

//Será una interfaz "general" que aplicaremos en nuestro sistema cuando no se requieran especificaciones
public interface CrudService_Repository<T, ID> {

    //GET Todos
    List<T> getAll();

    //GET por Id
    Optional<T> getById(ID id);

    //POST
    T save(T entidad);

    //PUT
    T update(ID id, T entidad);

    //DELETE
    void deleteById(ID id);

}
