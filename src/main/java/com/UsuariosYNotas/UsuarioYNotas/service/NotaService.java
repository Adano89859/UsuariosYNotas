package com.UsuariosYNotas.UsuarioYNotas.service;

import com.UsuariosYNotas.UsuarioYNotas.model.Nota;
import com.UsuariosYNotas.UsuarioYNotas.model.Usuario;
import com.UsuariosYNotas.UsuarioYNotas.repository.NotaRepository;
import com.UsuariosYNotas.UsuarioYNotas.repository.UsuarioRepository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService extends AbstractCrudService<Nota, Long>{

    //Atributos
    private final NotaRepository notaRepository;
    private final UsuarioRepository usuarioRepository;

    //COSNTRUCTOR
    public  NotaService(NotaRepository notaRepository,UsuarioRepository usuarioRepository){
        super(notaRepository);
        this.notaRepository = notaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Nota updateNota(Nota updatedNota, Long id) {
        // Verificar si la Nota existe en la base de datos
        Optional<Nota> optionalNota = notaRepository.findById(id);

        //Paso de formato Opcional a formato Nota, si existe
        if(!optionalNota.isPresent()) {
            //Si no existe, ahgo que salte una excepción
            throw new IllegalArgumentException("No se encuentra la Nota");
        }else{

            // Obtener la Nota existente
            Nota existingNota = (Nota) optionalNota.get();

            // Aplicar las actualizaciones en el nota existente
            existingNota.setUsuario(updatedNota.getUsuario());
            existingNota.setFechaCreacion(updatedNota.getFechaCreacion());
            existingNota.setContenido(updatedNota.getContenido());
            existingNota.setTitulo(updatedNota.getTitulo());
            existingNota.setId(updatedNota.getId());

            // Guardar los cambios en la base de datos
            return notaRepository.save(existingNota);

        }
    }

    //Método que será usado en "BuscarSegunUsuario"
    public List<Nota> buscarNotaSegunUsuario(Long usuarioId, String sortBy, String orden){

        //Compruebo que el ID del usuario no sea nulo
        if(usuarioId==null){
            throw new IllegalArgumentException("No se introdujo ID del Usuario");
        }

        //Creo la variable sort que servirá para el orden
        Sort mostrarOrden;

        //Uso el String "orden" para saber de qué manera los voy a ordenar; el "IgnoreCase" es para que no me de error si es null
        if(orden.equalsIgnoreCase("desc")){
            //Ordeno de forma descendente
            mostrarOrden = Sort.by(sortBy).descending();
        }else{
            //Ordeno de forma ascendente
            mostrarOrden = Sort.by(sortBy).ascending();
        }

        //Saco las notas que tenga el usuario
        return notaRepository.findByUsuarioId(usuarioId,mostrarOrden);
    }

    //Método que guarda la nota para un Usuario concreto
    public Nota guardarNotaUsuario(Long usuarioId, Nota nota){

        //Compruebo que el usuario existe
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioId);
        if(optionalUsuario.isPresent()){
            //Extraigo el Usuario del optional Usuario
            Usuario usuario = optionalUsuario.get();
            //Si el usuario existe, guardo la nota con asignación a su ID
            nota.setUsuario(usuario);

            return notaRepository.save(nota);
        }else{
            //Si no existe doy error
            throw new IllegalArgumentException("El Usuario introducido no existe");
        }

    }

}
