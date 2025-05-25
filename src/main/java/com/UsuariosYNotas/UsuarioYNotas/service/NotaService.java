package com.UsuariosYNotas.UsuarioYNotas.service;

import com.UsuariosYNotas.UsuarioYNotas.repository.NotaRepository;

public class NotaService extends AbstractCrudService{

    //Atributos


    //COSNTRUCTOR
    public  NotaService(NotaRepository notaRepository){
        super(notaRepository);
    }

}
