package com.donacion.modulo2.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "historial_receptor")
public class HistorialReceptor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idHistorial;

    @Column(nullable = false)
    private LocalDateTime fechaEntrega;

    @Column(length = 255)
    private String observacion;

    // Relación ManyToOne con Receptor
    @ManyToOne
    @JoinColumn(name = "id_receptor", nullable = false)
    private Receptor receptor;

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

    public Receptor getReceptor() {
        return receptor;
    }

    public void setReceptor(Receptor receptor) {
        this.receptor = receptor;
    }
}
