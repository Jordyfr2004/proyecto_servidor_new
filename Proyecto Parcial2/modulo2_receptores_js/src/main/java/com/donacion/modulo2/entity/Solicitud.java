package com.donacion.modulo2.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import jakarta.validation.constraints.Pattern;

/**
 * Entidad Solicitud.
 * Representa una solicitud de víveres realizada por un receptor.
 */
@Entity
@Table(name = "solicitudes", 
    indexes = {
        @Index(name = "idx_solicitud_receptor", columnList = "id_receptor"),
        @Index(name = "idx_solicitud_estado", columnList = "estado"),
        @Index(name = "idx_solicitud_fecha", columnList = "fechaSolicitud")
})
public class Solicitud {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_solicitud", updatable = false, nullable = false)
    private UUID idSolicitud;

    @NotBlank(message = "El tipo de donación es obligatorio")
    @Column(name = "tipo_donacion", nullable = false, length = 100)
    private String tipoDonacion;

    @NotBlank(message = "La descripción es obligatoria")
    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    @NotNull(message = "La fecha es obligatoria")
    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDateTime fechaSolicitud;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "PENDIENTE|APROBADA|RECHAZADA|ENTREGADA|CANCELADA", 
            message = "Estado debe ser: PENDIENTE, APROBADA, RECHAZADA, ENTREGADA o CANCELADA")
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    // Relación con Receptor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_receptor", nullable = false)
    private Receptor receptor;

    // Relación bidireccional con Entrega
    @OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Entrega> entregas = new ArrayList<>();

    // Constructores
    public Solicitud() {}

    public Solicitud(String tipoDonacion, String descripcion, String estado, Receptor receptor) {
        this.tipoDonacion = tipoDonacion;
        this.descripcion = descripcion;
        this.estado = estado;
        this.receptor = receptor;
    }

    // Método que se ejecuta antes de persistir
    @PrePersist
    protected void onCreate() {
        if (fechaSolicitud == null) {
            fechaSolicitud = LocalDateTime.now();
        }
        if (estado == null) {
            estado = "PENDIENTE";
        }
    }

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

    public Receptor getReceptor() {
        return receptor;
    }

    public void setReceptor(Receptor receptor) {
        this.receptor = receptor;
    }

    public List<Entrega> getEntregas() {
        return entregas;
    }

    public void setEntregas(List<Entrega> entregas) {
        this.entregas = entregas;
    }

    // Métodos auxiliares
    
    /**
     * Agrega una entrega a esta solicitud.
     *
     * @param entrega La entrega a agregar
     */
    public void agregarEntrega(Entrega entrega) {
        entregas.add(entrega);
        entrega.setSolicitud(this);
    }

    /**
     * Remueve una entrega de esta solicitud.
     *
     * @param entrega La entrega a remover
     */
    public void removerEntrega(Entrega entrega) {
        entregas.remove(entrega);
        entrega.setSolicitud(null);
    }

    /**
     * Verifica si la solicitud está en estado pendiente.
     *
     * @return true si está pendiente
     */
    public boolean isPendiente() {
        return "PENDIENTE".equals(estado);
    }

    /**
     * Verifica si la solicitud está aprobada.
     *
     * @return true si está aprobada
     */
    public boolean isAprobada() {
        return "APROBADA".equals(estado);
    }

    /**
     * Verifica si la solicitud está entregada.
     *
     * @return true si está entregada
     */
    public boolean isEntregada() {
        return "ENTREGADA".equals(estado);
    }

    /**
     * Obtiene el total de entregas realizadas.
     *
     * @return número de entregas
     */
    public int getTotalEntregas() {
        return entregas != null ? entregas.size() : 0;
    }
}