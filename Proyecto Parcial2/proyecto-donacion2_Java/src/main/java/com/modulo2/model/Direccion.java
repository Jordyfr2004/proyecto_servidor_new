package com.modulo2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Direccion {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String calle;
private String ciudad;
private String provincia;
private String codigoPostal; // ← este campo estaba faltando

@ManyToOne
@JoinColumn(name = "receptor_id", nullable = false)
private Receptor receptor;

public Direccion() {}

public Direccion(String calle, String ciudad, String provincia, String codigoPostal, Receptor receptor) {
    this.calle = calle;
    this.ciudad = ciudad;
    this.provincia = provincia;
    this.codigoPostal = codigoPostal;
    this.receptor = receptor;
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
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

public String getCodigoPostal() {
    return codigoPostal;
}

public void setCodigoPostal(String codigoPostal) {
    this.codigoPostal = codigoPostal;
}

public Receptor getReceptor() {
    return receptor;
}

public void setReceptor(Receptor receptor) {
    this.receptor = receptor;
}

}