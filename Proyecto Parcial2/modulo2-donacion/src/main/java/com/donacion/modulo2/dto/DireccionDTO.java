package com.donacion.modulo2.dto;

import java.util.UUID;

public class DireccionDTO {

    private UUID idDireccion;
    private String calle;
    private String ciudad;
    private String provincia;
    private String referencia;
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
