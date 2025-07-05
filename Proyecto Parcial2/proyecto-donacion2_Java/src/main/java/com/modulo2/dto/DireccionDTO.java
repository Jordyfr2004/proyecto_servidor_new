package com.modulo2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DireccionDTO {

private Long id;

@NotBlank(message = "La calle no puede estar vacía")
@Size(max = 100, message = "La calle no debe superar los 100 caracteres")
private String calle;

@NotBlank(message = "La ciudad no puede estar vacía")
@Size(max = 50, message = "La ciudad no debe superar los 50 caracteres")
private String ciudad;

@NotBlank(message = "La provincia no puede estar vacía")
@Size(max = 50, message = "La provincia no debe superar los 50 caracteres")
private String provincia;

@NotBlank(message = "El código postal no puede estar vacío")
@Size(max = 10, message = "El código postal no debe superar los 10 caracteres")
private String codigoPostal;

@NotNull(message = "Debe proporcionar el ID del receptor")
private Long receptorId;

public DireccionDTO() {}

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

public Long getReceptorId() {
    return receptorId;
}

public void setReceptorId(Long receptorId) {
    this.receptorId = receptorId;
}

}