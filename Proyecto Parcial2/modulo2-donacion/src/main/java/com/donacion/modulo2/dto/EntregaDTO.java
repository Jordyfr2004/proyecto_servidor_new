package com.donacion.modulo2.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * EntregaDTO:
 * Transfer Object para enviar y recibir datos de Entrega desde el frontend.
 */
public class EntregaDTO {

    private UUID idEntrega;

    @NotNull(message = "La fecha de entrega es obligatoria")
    private LocalDateTime fechaEntrega;

    @NotBlank(message = "La observación no puede estar vacía")
    private String observacion;

    @NotNull(message = "El ID de la solicitud es obligatorio")
    private UUID idSolicitud;

    @NotNull(message = "El ID de la donación es obligatorio")
    private UUID idDonacion;

    // Getters y Setters

    public UUID getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(UUID idEntrega) {
        this.idEntrega = idEntrega;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDateTime fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public UUID getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(UUID idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public UUID getIdDonacion() {
        return idDonacion;
    }

    public void setIdDonacion(UUID idDonacion) {
        this.idDonacion = idDonacion;
    }
}
