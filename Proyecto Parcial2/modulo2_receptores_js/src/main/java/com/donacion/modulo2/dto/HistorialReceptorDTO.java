package com.donacion.modulo2.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.donacion.modulo2.entity.HistorialReceptor.Estado;
import com.donacion.modulo2.entity.HistorialReceptor.TipoEvento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * HistorialReceptorDTO:
 * DTO con validaciones para gestionar el historial de receptores.
 */
public class HistorialReceptorDTO {

    private UUID idHistorial;

    @NotNull(message = "La fecha de entrega es obligatoria")
    private LocalDateTime fechaEntrega;

    @NotBlank(message = "La observación no puede estar vacía")
    @Size(max = 500, message = "La observación no puede exceder 500 caracteres")
    private String observacion;

    private LocalDateTime fechaRegistro;

    private LocalDateTime fechaActualizacion;

    private TipoEvento tipoEvento;

    private Estado estado;

    @Size(max = 200, message = "El detalle adicional no puede exceder 200 caracteres")
    private String detalleAdicional;

    private Double cantidadEntregada;

    @Size(max = 50, message = "La unidad no puede exceder 50 caracteres")
    private String unidad;

    private Double valorEstimado;

    @Size(max = 100, message = "El usuario registrador no puede exceder 100 caracteres")
    private String usuarioRegistrador;

    @NotNull(message = "El ID del receptor es obligatorio")
    private UUID idReceptor; // Para creación/actualización - mantener para compatibilidad

    // ID de la entrega asociada (puede ser null para eventos que no sean entregas)
    private UUID idEntrega; // Para creación/actualización - mantener para compatibilidad

    // ========== ADAPTADOR PARA INTEGRACIÓN CON MÓDULO DE DONACIÓN ==========
    // DESCRIPCIÓN: Campo agregado como String para aceptar IDs numéricos 
    // del módulo de donación (ej: "1", "2", "123") que se convierten automáticamente
    // a UUID internamente manteniendo nuestra arquitectura UUID
    // PROPÓSITO: Registrar donaciones recibidas según PDF "Historial de receptor – Donaciones recibidas"
    // TEMPORAL: Comentado para testing - el módulo de donación no está disponible
    // @NotNull(message = "El ID de la donación es obligatorio")
    private String idDonacion; // Campo String para aceptar IDs numéricos ("1", "2", etc.)

    // Respuestas anidadas - información completa de entidades relacionadas
    private ReceptorDTO receptor;
    private EntregaDTO entrega;

    // Constructores
    public HistorialReceptorDTO() {}

    // Getters y setters
    public UUID getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(UUID idHistorial) {
        this.idHistorial = idHistorial;
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

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getDetalleAdicional() {
        return detalleAdicional;
    }

    public void setDetalleAdicional(String detalleAdicional) {
        this.detalleAdicional = detalleAdicional;
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

    public Double getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(Double valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public String getUsuarioRegistrador() {
        return usuarioRegistrador;
    }

    public void setUsuarioRegistrador(String usuarioRegistrador) {
        this.usuarioRegistrador = usuarioRegistrador;
    }

    public UUID getIdReceptor() {
        return idReceptor;
    }

    public void setIdReceptor(UUID idReceptor) {
        this.idReceptor = idReceptor;
    }

    public UUID getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(UUID idEntrega) {
        this.idEntrega = idEntrega;
    }

    public String getIdDonacion() {
        return idDonacion;
    }

    public void setIdDonacion(String idDonacion) {
        this.idDonacion = idDonacion;
    }

    public ReceptorDTO getReceptor() {
        return receptor;
    }

    public void setReceptor(ReceptorDTO receptor) {
        this.receptor = receptor;
    }

    public EntregaDTO getEntrega() {
        return entrega;
    }

    public void setEntrega(EntregaDTO entrega) {
        this.entrega = entrega;
    }
}
