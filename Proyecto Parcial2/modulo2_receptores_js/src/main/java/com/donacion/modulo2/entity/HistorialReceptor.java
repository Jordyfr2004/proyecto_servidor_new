package com.donacion.modulo2.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entidad HistorialReceptor:
 * Representa el historial de actividades y entregas de un receptor de víveres.
 */
@Entity
@Table(name = "historial_receptor",
    indexes = {
        @Index(name = "idx_historial_receptor", columnList = "id_receptor"),
        @Index(name = "idx_historial_entrega", columnList = "id_entrega"),
        @Index(name = "idx_historial_fecha_entrega", columnList = "fecha_entrega"),
        @Index(name = "idx_historial_tipo_evento", columnList = "tipo_evento"),
        @Index(name = "idx_historial_estado", columnList = "estado")
    })
public class HistorialReceptor {

    public enum TipoEvento {
        REGISTRO, ENTREGA, ACTUALIZACION, SOLICITUD, CANCELACION, VERIFICACION
    }

    public enum Estado {
        ACTIVO, INACTIVO, PENDIENTE, COMPLETADO, CANCELADO
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_historial", updatable = false, nullable = false)
    private UUID idHistorial;

    @NotNull(message = "La fecha de entrega es obligatoria")
    @Column(name = "fecha_entrega", nullable = false)
    private LocalDateTime fechaEntrega;

    @NotBlank(message = "La observación no puede estar vacía")
    @Size(max = 500, message = "La observación no puede exceder 500 caracteres")
    @Column(name = "observacion", nullable = false, length = 500)
    private String observacion;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_evento", length = 20, nullable = false)
    private TipoEvento tipoEvento = TipoEvento.ENTREGA;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 20, nullable = false)
    private Estado estado = Estado.COMPLETADO;

    @Size(max = 200, message = "El detalle adicional no puede exceder 200 caracteres")
    @Column(name = "detalle_adicional", length = 200)
    private String detalleAdicional;

    @Column(name = "cantidad_entregada")
    private Double cantidadEntregada;

    @Size(max = 50, message = "La unidad no puede exceder 50 caracteres")
    @Column(name = "unidad", length = 50)
    private String unidad;

    @Column(name = "valor_estimado")
    private Double valorEstimado;

    @Size(max = 100, message = "El usuario registrador no puede exceder 100 caracteres")
    @Column(name = "usuario_registrador", length = 100)
    private String usuarioRegistrador;

    // Relación ManyToOne con Receptor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_receptor", nullable = false)
    private Receptor receptor;

    // Relación ManyToOne con Entrega
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entrega")
    private Entrega entrega;

    // ========== CAMPO PARA INTEGRACIÓN CON MÓDULO DE DONACIÓN ==========
    // DESCRIPCIÓN: Campo UUID para almacenar internamente IDs de donación convertidos
    // desde formato numérico del módulo de donación externo
    // PROPÓSITO: Registrar donaciones recibidas según PDF "Historial de receptor – Donaciones recibidas"
    @Column(name = "id_donacion")
    private UUID idDonacion; // Almacenamiento interno como UUID

    // Constructores
    public HistorialReceptor() {}

    public HistorialReceptor(String observacion, TipoEvento tipoEvento, Receptor receptor) {
        this.observacion = observacion;
        this.tipoEvento = tipoEvento;
        this.receptor = receptor;
        this.fechaEntrega = LocalDateTime.now();
    }

    public HistorialReceptor(String observacion, TipoEvento tipoEvento, Receptor receptor, Entrega entrega) {
        this(observacion, tipoEvento, receptor);
        this.entrega = entrega;
    }

    public HistorialReceptor(String observacion, TipoEvento tipoEvento, Estado estado,
            Double cantidadEntregada, String unidad, Receptor receptor) {
        this(observacion, tipoEvento, receptor);
        this.estado = estado;
        this.cantidadEntregada = cantidadEntregada;
        this.unidad = unidad;
    }

    public HistorialReceptor(String observacion, TipoEvento tipoEvento, Estado estado,
            Double cantidadEntregada, String unidad, Receptor receptor, Entrega entrega) {
        this(observacion, tipoEvento, estado, cantidadEntregada, unidad, receptor);
        this.entrega = entrega;
    }

    // Métodos que se ejecutan antes de persistir y actualizar
    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDateTime.now();
        if (fechaEntrega == null) {
            fechaEntrega = LocalDateTime.now();
        }
        if (tipoEvento == null) {
            tipoEvento = TipoEvento.ENTREGA;
        }
        if (estado == null) {
            estado = Estado.COMPLETADO;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }

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

    public Receptor getReceptor() {
        return receptor;
    }

    public void setReceptor(Receptor receptor) {
        this.receptor = receptor;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public UUID getIdDonacion() {
        return idDonacion;
    }

    public void setIdDonacion(UUID idDonacion) {
        this.idDonacion = idDonacion;
    }

    // Métodos auxiliares

    /**
     * Verifica si el historial está completado.
     *
     * @return true si está completado
     */
    public boolean isCompletado() {
        return Estado.COMPLETADO.equals(estado);
    }

    /**
     * Verifica si el historial está pendiente.
     *
     * @return true si está pendiente
     */
    public boolean isPendiente() {
        return Estado.PENDIENTE.equals(estado);
    }

    /**
     * Verifica si el historial está activo.
     *
     * @return true si está activo
     */
    public boolean isActivo() {
        return Estado.ACTIVO.equals(estado);
    }

    /**
     * Verifica si es una entrega.
     *
     * @return true si es tipo entrega
     */
    public boolean isEntrega() {
        return TipoEvento.ENTREGA.equals(tipoEvento);
    }

    /**
     * Obtiene un resumen del historial.
     *
     * @return string con resumen
     */
    public String getResumen() {
        StringBuilder sb = new StringBuilder();
        sb.append(tipoEvento.name()).append(" - ").append(estado.name());
        if (cantidadEntregada != null) {
            sb.append(" (").append(cantidadEntregada);
            if (unidad != null) {
                sb.append(" ").append(unidad);
            }
            sb.append(")");
        }
        return sb.toString();
    }

    /**
     * Marca el historial como completado.
     */
    public void completar() {
        this.estado = Estado.COMPLETADO;
        this.fechaEntrega = LocalDateTime.now();
    }

    /**
     * Marca el historial como cancelado.
     */
    public void cancelar() {
        this.estado = Estado.CANCELADO;
    }

    /**
     * Establece información de la entrega.
     *
     * @param cantidad cantidad entregada
     * @param unidad   unidad de medida
     * @param valor    valor estimado
     */
    public void establecerEntrega(Double cantidad, String unidad, Double valor) {
        this.cantidadEntregada = cantidad;
        this.unidad = unidad;
        this.valorEstimado = valor;
        this.tipoEvento = TipoEvento.ENTREGA;
        this.estado = Estado.COMPLETADO;
    }
}
