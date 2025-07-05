package com.modulo2.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class HistorialReceptorDTO {

private Long id;

@NotNull(message = "La fecha de registro no puede ser nula")
private LocalDate fechaRegistro;

@NotBlank(message = "La descripción no puede estar vacía")
@Size(min = 5, max = 255, message = "La descripción debe tener entre 5 y 255 caracteres")
private String descripcion;

@NotNull(message = "Debe proporcionar el ID del receptor")
private Long receptorId;

public HistorialReceptorDTO() {}

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

public Long getReceptorId() {
    return receptorId;
}

public void setReceptorId(Long receptorId) {
    this.receptorId = receptorId;
}

}