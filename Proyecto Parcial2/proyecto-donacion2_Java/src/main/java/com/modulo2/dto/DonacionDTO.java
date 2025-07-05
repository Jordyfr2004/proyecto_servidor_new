package com.modulo2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DonacionDTO {

private Long id;

@NotBlank(message = "La descripción no puede estar vacía")
@Size(min = 5, max = 100, message = "La descripción debe tener entre 5 y 100 caracteres")
private String descripcion;

public DonacionDTO() {}

public DonacionDTO(Long id, String descripcion) {
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