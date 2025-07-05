package com.modulo2.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SolicitudDTO {

    private Long id;

    @NotNull(message = "La fecha de solicitud es obligatoria")
    private LocalDate fechaSolicitud;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotBlank(message = "La observación es obligatoria")
    private String observacion;

    @NotNull(message = "El receptor es obligatorio")
    private ReceptorDTO receptor;

    public SolicitudDTO() {}

    public SolicitudDTO(Long id, LocalDate fechaSolicitud, String estado, String observacion, ReceptorDTO receptor) {
        this.id = id;
        this.fechaSolicitud = fechaSolicitud;
        this.estado = estado;
        this.observacion = observacion;
        this.receptor = receptor;
    }

    // Getters y Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDate fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }

    public ReceptorDTO getReceptor() { return receptor; }
    public void setReceptor(ReceptorDTO receptor) { this.receptor = receptor; }
}
