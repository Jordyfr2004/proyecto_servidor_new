package com.modulo2.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

// Captura errores de validaciones tipo @NotBlank, @NotNull, etc. en @RequestBody
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("status", 400);
    response.put("error", "Validación fallida");

    Map<String, String> errores = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error ->
            errores.put(error.getField(), error.getDefaultMessage()));
    response.put("errores", errores);

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
}

// Captura errores de validaciones en parámetros (por ejemplo, @Valid en un @PathVariable o @RequestParam)
@ExceptionHandler(ConstraintViolationException.class)
public ResponseEntity<Map<String, Object>> handleConstraintViolation(ConstraintViolationException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("status", 400);
    response.put("error", "Restricción inválida");
    response.put("mensaje", ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
}

// Captura cualquier otra excepción no controlada
@ExceptionHandler(Exception.class)
public ResponseEntity<Map<String, Object>> handleGeneralError(Exception ex) {
    Map<String, Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("status", 500);
    response.put("error", "Error interno del servidor");
    response.put("mensaje", ex.getMessage());
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
}

}