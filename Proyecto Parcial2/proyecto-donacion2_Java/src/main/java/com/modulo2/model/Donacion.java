package com.modulo2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Donacion {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String descripcion;

public Donacion() {}

public Donacion(Long id, String descripcion) {
    this.id = id;
    this.descripcion = descripcion;
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getDescripcion() {
    return descripcion;
}

public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
}

}