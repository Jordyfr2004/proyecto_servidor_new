package com.modulo2.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EntregaDTO {

private Long id;

@NotNull(message = "La fecha de entrega no puede ser nula")
private LocalDate fechaEntrega;

@NotNull(message = "La observación no puede ser nula")
@Size(min = 5, max = 255, message = "La observación debe tener entre 5 y 255 caracteres")
private String observacion;

@NotNull(message = "Debe proporcionar el ID de la solicitud")
private Long solicitudId;

@NotNull(message = "Debe proporcionar el ID de la donación")
private Long donacionId;

public EntregaDTO() {}

public Long getId() {
    return id;
}

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

public Long getSolicitudId() {
    return solicitudId;
}

public void setSolicitudId(Long solicitudId) {
    this.solicitudId = solicitudId;
}

public Long getDonacionId() {
    return donacionId;
}

public void setDonacionId(Long donacionId) {
    this.donacionId = donacionId;
}

}