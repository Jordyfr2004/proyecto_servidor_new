package com.donacion.modulo2.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * EntregaDTO:
 * Transfer Object para enviar y recibir datos de Entrega desde el frontend.
 */
public class EntregaDTO {

    private UUID idEntrega;

    @NotNull(message = "La fecha de entrega es obligatoria")
    private LocalDateTime fechaEntrega;

    @NotBlank(message = "La observación no puede estar vacía")
    @Size(max = 500, message = "La observación no puede exceder 500 caracteres")
    private String observacion;

    private Double cantidadEntregada;

    @Size(max = 50, message = "La unidad no puede exceder 50 caracteres")
    private String unidad;

    private String estadoEntrega;

    @NotNull(message = "El ID de la solicitud es obligatorio")
    private UUID idSolicitud; // Para creación/actualización - mantener para compatibilidad

    // ========== ADAPTADOR PARA INTEGRACIÓN CON MÓDULO DE DONACIÓN ==========
    // DESCRIPCIÓN: Campo cambiado de UUID a String para aceptar IDs numéricos 
    // del módulo de donación (ej: "1", "2", "123") que se convierten automáticamente
    // a UUID internamente manteniendo nuestra arquitectura UUID
    // TEMPORAL: Comentado para testing - el módulo de donación no está disponible
    // @NotNull(message = "El ID de la donación es obligatorio")
    private String idDonacion; // Cambiado de UUID a String para aceptar IDs numéricos ("1", "2", etc.)

    // Lista de IDs de historial de entregas (navegación de relación)
    private List<UUID> idsHistorialEntregas;

    // Respuesta anidada - información completa de la solicitud (que incluye receptor)
    private SolicitudDTO solicitud;

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

    public Double getCantidadEntregada() {
        return cantidadEntregada;
    }

    public void setCantidadEntregada(Double cantidadEntregada) {
        this.cantidadEntregada = cantidadEntregada;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getEstadoEntrega() {
        return estadoEntrega;
    }

    public void setEstadoEntrega(String estadoEntrega) {
        this.estadoEntrega = estadoEntrega;
    }

    public UUID getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(UUID idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getIdDonacion() {
        return idDonacion;
    }

    public void setIdDonacion(String idDonacion) {
        this.idDonacion = idDonacion;
    }

    public List<UUID> getIdsHistorialEntregas() {
        return idsHistorialEntregas;
    }

    public void setIdsHistorialEntregas(List<UUID> idsHistorialEntregas) {
        this.idsHistorialEntregas = idsHistorialEntregas;
    }

    public SolicitudDTO getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudDTO solicitud) {
        this.solicitud = solicitud;
    }
}
