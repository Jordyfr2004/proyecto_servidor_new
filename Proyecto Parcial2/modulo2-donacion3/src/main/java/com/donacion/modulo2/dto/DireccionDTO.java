package com.donacion.modulo2.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DireccionDTO:
 * DTO para transferir datos relacionados con direcciones de los receptores.
 * Incluye validaciones para campos obligatorios.
 */
public class DireccionDTO {

    private UUID idDireccion;

    @NotBlank(message = "La calle es obligatoria")
    private String calle;

    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;

    @NotBlank(message = "La provincia es obligatoria")
    private String provincia;

    @NotBlank(message = "La referencia es obligatoria")
    private String referencia;

    @NotNull(message = "El ID del receptor es obligatorio")
    private UUID idReceptor; // Solo enviamos el UUID del receptor

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

    public UUID getIdReceptor() {
        return idReceptor;
    }

    public void setIdReceptor(UUID idReceptor) {
        this.idReceptor = idReceptor;
    }
}
