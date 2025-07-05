package com.modulo2.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Entrega {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private LocalDate fechaEntrega;
private String observacion;

@ManyToOne
@JoinColumn(name = "solicitud_id", nullable = false)
private Solicitud solicitud;

@ManyToOne
@JoinColumn(name = "donacion_id", nullable = false)
private Donacion donacion;

public Entrega() {
}

public Entrega(LocalDate fechaEntrega, String observacion, Solicitud solicitud, Donacion donacion) {
    this.fechaEntrega = fechaEntrega;
    this.observacion = observacion;
    this.solicitud = solicitud;
    this.donacion = donacion;
}

public Long getId() {
    return id;
}

// Este método es el que faltaba
public void setId(Long id) {
    this.id = id;
}

public LocalDate getFechaEntrega() {
    return fechaEntrega;
}

public void setFechaEntrega(LocalDate fechaEntrega) {
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

public Donacion getDonacion() {
    return donacion;
}

public void setDonacion(Donacion donacion) {
    this.donacion = donacion;
}
}