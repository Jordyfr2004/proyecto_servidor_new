package com.modulo2.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class HistorialReceptor {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private LocalDate fechaRegistro;

private String descripcion;

@ManyToOne
@JoinColumn(name = "receptor_id", nullable = false)
private Receptor receptor;

public HistorialReceptor() {}

public HistorialReceptor(LocalDate fechaRegistro, String descripcion, Receptor receptor) {
    this.fechaRegistro = fechaRegistro;
    this.descripcion = descripcion;
    this.receptor = receptor;
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public LocalDate getFechaRegistro() {
    return fechaRegistro;
}

public void setFechaRegistro(LocalDate fechaRegistro) {
    this.fechaRegistro = fechaRegistro;
}

public String getDescripcion() {
    return descripcion;
}

public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
}

public Receptor getReceptor() {
    return receptor;
}

public void setReceptor(Receptor receptor) {
    this.receptor = receptor;
}

}