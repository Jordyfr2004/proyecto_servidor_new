package com.donacion.modulo2.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entidad Entrega:
 * Representa la entrega realizada para una solicitud de víveres.
 */
@Entity
@Table(name = "entregas",
    indexes = {
        @Index(name = "idx_entrega_solicitud", columnList = "id_solicitud"),
        @Index(name = "idx_entrega_fecha", columnList = "fecha_entrega"),
        @Index(name = "idx_entrega_donacion", columnList = "id_donacion")
    })
public class Entrega {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_entrega", updatable = false, nullable = false)
    private UUID idEntrega;

    @NotNull(message = "La fecha de entrega es obligatoria")
    @Column(name = "fecha_entrega", nullable = false)
    private LocalDateTime fechaEntrega;

    @NotBlank(message = "La observación no puede estar vacía")
    @Size(max = 500, message = "La observación no puede exceder 500 caracteres")
    @Column(name = "observacion", nullable = false, length = 500)
    private String observacion;

    @Column(name = "cantidad_entregada")
    private Double cantidadEntregada;

    @Size(max = 50, message = "La unidad no puede exceder 50 caracteres")
    @Column(name = "unidad", length = 50)
    private String unidad;

    @Column(name = "estado_entrega", length = 20)
    private String estadoEntrega = "COMPLETADA";

    // Relación con Solicitud
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud", nullable = false)
    private Solicitud solicitud;

    // Simulación de relación con Donación (FK lógica solamente)
    // TEMPORAL: Comentado para testing - el módulo de donación no está disponible
    // @NotNull(message = "El ID de donación es obligatorio")
    @Column(name = "id_donacion", nullable = true)
    private UUID idDonacion;

    // Relación inversa con HistorialReceptor
    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HistorialReceptor> historialEntregas;

    // Constructores
    public Entrega() {}

    public Entrega(String observacion, Solicitud solicitud, UUID idDonacion) {
        this.observacion = observacion;
        this.solicitud = solicitud;
        this.idDonacion = idDonacion;
    }

    public Entrega(String observacion, Double cantidadEntregada, String unidad, 
            Solicitud solicitud, UUID idDonacion) {
        this.observacion = observacion;
        this.cantidadEntregada = cantidadEntregada;
        this.unidad = unidad;
        this.solicitud = solicitud;
        this.idDonacion = idDonacion;
    }

    // Método que se ejecuta antes de persistir
    @PrePersist
    protected void onCreate() {
        if (fechaEntrega == null) {
            fechaEntrega = LocalDateTime.now();
        }
        if (estadoEntrega == null) {
            estadoEntrega = "COMPLETADA";
        }
    }

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

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public UUID getIdDonacion() {
        return idDonacion;
    }

    public void setIdDonacion(UUID idDonacion) {
        this.idDonacion = idDonacion;
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

    public List<HistorialReceptor> getHistorialEntregas() {
        return historialEntregas;
    }

    public void setHistorialEntregas(List<HistorialReceptor> historialEntregas) {
        this.historialEntregas = historialEntregas;
    }

    // Métodos auxiliares

    /**
     * Verifica si la entrega está completada.
     *
     * @return true si está completada
     */
    public boolean isCompletada() {
        return "COMPLETADA".equals(estadoEntrega);
    }

    /**
     * Verifica si la entrega está pendiente.
     *
     * @return true si está pendiente
     */
    public boolean isPendiente() {
        return "PENDIENTE".equals(estadoEntrega);
    }

    /**
     * Obtiene información resumida de la entrega.
     *
     * @return string con resumen
     */
    public String getResumen() {
        StringBuilder sb = new StringBuilder();
        sb.append("Entrega ");
        if (cantidadEntregada != null) {
            sb.append(cantidadEntregada);
            if (unidad != null) {
                sb.append(" ").append(unidad);
            }
        }
        sb.append(" - ").append(estadoEntrega);
        return sb.toString();
    }

    /**
     * Marca la entrega como completada y asigna la fecha actual.
     */
    public void completar() {
        this.estadoEntrega = "COMPLETADA";
        this.fechaEntrega = LocalDateTime.now();
    }
}
