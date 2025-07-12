package com.donacion.modulo2.exception;

/**
 * RecursoNoEncontradoException:
 * Excepción personalizada para recursos no encontrados.
 */
public class RecursoNoEncontradoException extends RuntimeException {
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
