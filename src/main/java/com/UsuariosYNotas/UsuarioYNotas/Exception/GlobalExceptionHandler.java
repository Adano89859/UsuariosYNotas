package com.UsuariosYNotas.UsuarioYNotas.Exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class GlobalExceptionHandler {

    //Si da un error de tipo ethodArgumentNotValidException, se recurrirá a este método
    @ExceptionHandler(MethodArgumentNotValidException.class)
    //Los dos String son el nombre de campo y el mensaje de error que va a tener el map
    //"ex" va a contener los errores de validación detecatdos
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        //Creo un mapa para almacenar los errores
        Map<String, String> errores = new HashMap<>();

        //Se recorren todos los errores de "ex" y se traspasan a "errores"
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            //Con getField() otengo el nombre del campo, y con getDefaultMessage() el mensaje
            errores.put(error.getField(), error.getDefaultMessage());
        }

        //Se devuelve el mapa con los errores
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

    // Manejo de errores por restricciones  @NotNull, @Size
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException ex) {
        //Devuelvo el error que dió
        return new ResponseEntity<>("Error de validación: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Para errores lógicos como falta de existencia de usuario
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        //Devuelvo el texto del error que ha dado
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Manejo de entidad no encontrada, usados con los Optional; relacionado con la falta de ID
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(NoSuchElementException ex) {
        //Devuelvo el error y el mensaje del mismo
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Error genérico, cualquier otro que no esté visto en los métodos anteriores
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        //Doy el error con su mensaje
        return new ResponseEntity<>("Ha ocurrido un error inesperado: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
