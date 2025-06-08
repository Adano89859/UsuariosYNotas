package com.UsuariosYNotas.UsuarioYNotas.controller;

import com.UsuariosYNotas.UsuarioYNotas.model.Nota;
import com.UsuariosYNotas.UsuarioYNotas.service.NotaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/notas")
public class NotaController {

    //ATRIBUTOS
    private final NotaServiceImpl notaServiceImpl;

    //CONSTRUCTOR
    public NotaController(NotaServiceImpl notaServiceImpl) {
        this.notaServiceImpl = notaServiceImpl;
    }

    //LISTAR LAS NOTAS
    @GetMapping
    public List<Nota> getNota(){ return notaServiceImpl.getAll(); }

    //BUSCAR NOTA POR ID
    @GetMapping("/{id}")
    @ResponseBody
    public Optional<Nota> getNotaID(@PathVariable("id") Long ID) {
        Optional<Nota> nota = notaServiceImpl.getById(ID);
        //Añado el responseEntity para que no de errores en el postman
        return ResponseEntity.ok(nota).getBody();
    }

    //CREAR NOTA NUEVA
    @PostMapping
    public Nota createNota(
            @RequestParam Long usuarioId,
            @RequestBody @Valid Nota nota) {

        return (Nota) notaServiceImpl.guardarNotaUsuario(usuarioId,nota);
    }

    //ACTUALIZAR NOTA
    @PutMapping("/{id}")
    public ResponseEntity<Nota> updateNota(@PathVariable("id") Long id, @RequestBody @Valid Nota nota) {

        try {
            Nota updated = notaServiceImpl.updateNota(nota, id);
            return ResponseEntity.ok(updated);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //ELIMINAR NOTA POR ID
    @DeleteMapping("/{id}")
    public void deleteNota(@PathVariable("id") Long ID){
        notaServiceImpl.deleteById(ID);
    }

    //GET DE BUSCAR NOTAS POR UDUARIO
    @GetMapping("/buscarNotasPorUsuario")
    public List<Nota> buscarNotasPorUsuario
    (@RequestParam(required = false) Long usuarioId,
     @RequestParam(defaultValue = "id") String sortBy,
     @RequestParam(defaultValue = "asc") String orden
     ){

        return notaServiceImpl.buscarNotaSegunUsuario(usuarioId,sortBy,orden);
    }

}
