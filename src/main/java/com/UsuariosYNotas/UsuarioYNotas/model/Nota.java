package com.UsuariosYNotas.UsuarioYNotas.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Libro")
public class Nota {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    //Este atributo, como debe ser un tecto largo en la BBDD, le pongo la siguiente definición de columna
    @Column(columnDefinition = "TEXT")
    private String contenido;

    //Atributo de la Fecha y Hora
    private LocalDateTime fechaCreación;

    //Tengo que poner "ManyToOne" porque muchas notas pueden pertenecer a un mismo usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    //Métodos
    //CONSTRUCTOR
    public Nota(){}

    public Nota(Long id, String titulo, String contenido, LocalDateTime fechaCreación, Usuario usuario){
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaCreación = fechaCreación;
        this.usuario = usuario;
    }

    //GETTER Y SETTER


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaCreación() {
        return fechaCreación;
    }

    public void setFechaCreación(LocalDateTime fechaCreación) {
        this.fechaCreación = fechaCreación;
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
