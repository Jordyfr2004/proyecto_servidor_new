package com.donacion.modulo2.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DireccionDTO:
 * DTO para transferir datos relacionados con direcciones de los receptores.
 * Incluye validaciones para campos obligatorios.
 */
public class DireccionDTO {

    private UUID idDireccion;

    @NotBlank(message = "La calle es obligatoria")
    @Size(max = 200, message = "La calle no puede exceder 200 caracteres")
    private String calle;

    @NotBlank(message = "La ciudad es obligatoria")
    @Size(max = 100, message = "La ciudad no puede exceder 100 caracteres")
    private String ciudad;

    @NotBlank(message = "La provincia es obligatoria")
    @Size(max = 100, message = "La provincia no puede exceder 100 caracteres")
    private String provincia;

    @NotBlank(message = "La referencia es obligatoria")
    @Size(max = 500, message = "La referencia no puede exceder 500 caracteres")
    private String referencia;

    @Size(max = 20, message = "El código postal no puede exceder 20 caracteres")
    private String codigoPostal;

    private Double latitud;

    private Double longitud;

    private Boolean esPrincipal;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

    @NotNull(message = "El ID del receptor es obligatorio")
    private UUID idReceptor; // Para creación/actualización - mantener para compatibilidad

    // Respuesta anidada - información completa del receptor
    private ReceptorDTO receptor;

    // Constructores
    public DireccionDTO() {}

    // Getters y setters
    public UUID getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(UUID idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Boolean getEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(Boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public UUID getIdReceptor() {
        return idReceptor;
    }

    public void setIdReceptor(UUID idReceptor) {
        this.idReceptor = idReceptor;
    }

    public ReceptorDTO getReceptor() {
        return receptor;
    }

    public void setReceptor(ReceptorDTO receptor) {
        this.receptor = receptor;
    }
}
