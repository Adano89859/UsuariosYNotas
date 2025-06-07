package com.UsuariosYNotas.UsuarioYNotas.service;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudService<T, ID> implements CrudService_Repository<T, ID> {

    //Atributos
    protected final JpaRepository<T,ID> repository;

    //CONSTRUCTOR
    protected AbstractCrudService(JpaRepository<T,ID> repository){
        this.repository = repository;
    }

    @Override
    public List<T> getAll(){
        return repository.findAll();
    }

    @Override
    public Optional<T> getById(ID id){
        return repository.findById(id);
    }

    @Override
    public T save(T entidad){
        return repository.save(entidad);
    }

    @Override
    public T update(ID id, T entidad){
        //Compruebo que la entidad existe previamente
        if(!repository.existsById(id)){
            throw new IllegalArgumentException("No existe previamente la entidad con el ID "+id);
        }
        //Extraigo la entidad que ya existe
        T existente = repository.findById(id).orElseThrow();
        //Paso todos los atributos del objeto entrante al existente, sin modificar el id
        BeanUtils.copyProperties(entidad, existente, "id");
        //Guardo el objeto con las nuevas características
        return repository.save(existente);
    }

    @Override
    public void deleteById(ID id){
        repository.deleteById(id);
    }

}
