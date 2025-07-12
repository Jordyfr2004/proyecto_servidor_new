package com.donacion.modulo2.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * SolicitudDTO
 *
 * DTO utilizado para la transferencia de datos entre el cliente y el backend.
 * Incluye validaciones y un campo para relacionar con el receptor.
 */
public class SolicitudDTO {

    private UUID idSolicitud;

    @NotBlank(message = "El tipo de donación es obligatorio")
    private String tipoDonacion;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "La fecha de la solicitud es obligatoria")
    private LocalDateTime fechaSolicitud;

    @NotBlank(message = "El estado de la solicitud es obligatorio")
    private String estado;

    @NotNull(message = "El ID del receptor es obligatorio")
    private UUID idReceptor;

    // Getters y Setters

    public UUID getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(UUID idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getTipoDonacion() {
        return tipoDonacion;
    }

    public void setTipoDonacion(String tipoDonacion) {
        this.tipoDonacion = tipoDonacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public UUID getIdReceptor() {
        return idReceptor;
    }

    public void setIdReceptor(UUID idReceptor) {
        this.idReceptor = idReceptor;
    }
}
