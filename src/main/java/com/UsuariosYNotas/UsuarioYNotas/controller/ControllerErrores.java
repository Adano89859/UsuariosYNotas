package com.UsuariosYNotas.UsuarioYNotas.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ControllerErrores {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationError(MethodArgumentNotValidException exp){
        Map<String, String> errores = new HashMap<>();

        exp.getBindingResult().getFieldErrors().forEach(error ->{
            errores.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleCustomError(ResponseStatusException exp) {
        Map<String, String> error = new HashMap<>();
        error.put("error", exp.getReason());
        return ResponseEntity.status(exp.getStatusCode()).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleUnexpectedError(Exception exp){
        Map<String, String> error = new HashMap<>();
        error.put("error", "Error inesperado: " + exp.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}