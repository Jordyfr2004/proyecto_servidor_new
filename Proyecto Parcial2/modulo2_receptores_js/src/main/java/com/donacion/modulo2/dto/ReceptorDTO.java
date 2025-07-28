package com.donacion.modulo2.dto;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * ReceptorDTO
 * 
 * Esta clase se utiliza para transferir datos de entrada y salida entre el cliente y el backend.
 * Incluye validaciones para asegurar que los datos recibidos sean correctos antes de ser procesados.
 */
public class ReceptorDTO {

    private UUID idReceptor;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
    private String nombre;

    @NotBlank(message = "La cédula es obligatoria")
    @Size(min = 10, max = 10, message = "La cédula debe tener exactamente 10 dígitos")
    private String cedula;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 10, max = 10, message = "El teléfono debe tener exactamente 10 dígitos")
    private String telefono;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe tener un formato válido")
    private String correo;

    // Listas de IDs de entidades relacionadas (navegación de relaciones)
    private List<UUID> idsDirecciones;
    private List<UUID> idsSolicitudes;
    private List<UUID> idsHistorial;

    // Getters y Setters

    public UUID getIdReceptor() {
        return idReceptor;
    }

    public void setIdReceptor(UUID idReceptor) {
        this.idReceptor = idReceptor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<UUID> getIdsDirecciones() {
        return idsDirecciones;
    }

    public void setIdsDirecciones(List<UUID> idsDirecciones) {
        this.idsDirecciones = idsDirecciones;
    }

    public List<UUID> getIdsSolicitudes() {
        return idsSolicitudes;
    }

    public void setIdsSolicitudes(List<UUID> idsSolicitudes) {
        this.idsSolicitudes = idsSolicitudes;
    }

    public List<UUID> getIdsHistorial() {
        return idsHistorial;
    }

    public void setIdsHistorial(List<UUID> idsHistorial) {
        this.idsHistorial = idsHistorial;
    }
}
