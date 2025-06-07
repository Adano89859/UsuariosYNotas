package com.UsuariosYNotas.UsuarioYNotas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "nota")
public class Nota {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    @NotBlank(message = "La Nota debe tener un Título")
    @Size(max = 100)
    private String titulo;

    //Este atributo, como debe ser un texto largo en la BBDD, le pongo la siguiente definición de columna
    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "La Nota debe tener contenido")
    private String contenido;

    //Atributo de la Fecha y Hora
    @NotNull(message = "La Nota debe tener una fecha de creación")
    private LocalDateTime fechaCreacion;

    //Tengo que poner "ManyToOne" porque muchas notas pueden pertenecer a un mismo usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @NotNull(message = "La Nota debe tener un usuario propietario")
    private Usuario usuario;


    //Métodos
    //CONSTRUCTOR
    public Nota(){}

    public Nota(Long id, String titulo, String contenido, LocalDateTime fechaCreacion, Usuario usuario){
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
    }

    //GETTER Y SETTER


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreación) {
        this.fechaCreacion = fechaCreación;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
