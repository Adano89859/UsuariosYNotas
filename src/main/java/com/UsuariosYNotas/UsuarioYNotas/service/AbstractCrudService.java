package com.UsuariosYNotas.UsuarioYNotas.service;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractCrudService implements CrudService<Entidad, Long> {

    //Atributos
    protected final JpaRepository<Entidad,Long> repository;

    //CONSTRUCTOR
    protected AbstractCrudService(JpaRepository<Entidad,Long> repository){
        this.repository = repository;
    }

    @Override
    public List<Entidad> getAll(){
        return repository.findAll();
    }

    @Override
    public Optional<Entidad> getById(Long id){
        return repository.findById(id);
    }

    @Override
    public Entidad save(Entidad entidad){
        return repository.save(entidad);
    }

    @Override
    public Entidad update(Long id, Entidad entidad){
        //Compruebo que la entidad existe previamente
        if(!repository.existsById(id)){
            throw new IllegalArgumentException("No existe previamente la entidad con el ID "+id);
        }
        //Extraigo la entidad que ya existe
        Entidad existente = repository.findById(id).orElseThrow();
        //Paso todos los atributos del objeto entrante al existente, sin modificar el id
        BeanUtils.copyProperties(entidad, existente, "id");
        //Guardo el objeto con las nuevas características
        return repository.save(existente);
    }

    @Override
    public void deleteById(Long id){
        repository.deleteById(id);
    }

}
