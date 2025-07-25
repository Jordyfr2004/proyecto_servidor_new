package com.donacion.modulo2.entity;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Entidad Receptor.
 * Representa a una persona que solicita víveres.
 */
@Entity
@Table(name = "receptores")
public class Receptor {

    @Id
    @GeneratedValue(generator = "UUID") // Indica que se usará un generador llamado UUID
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator") // Generador para UUID
    @Column(name = "id_receptor", updatable = false, nullable = false) // Mapea la columna correctamente
    private UUID idReceptor;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
    private String nombre;

    @NotBlank(message = "La cédula no puede estar vacía")
    @Size(min = 10, max = 10, message = "La cédula debe tener 10 dígitos")
    private String cedula;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Size(min = 10, max = 10, message = "El teléfono debe tener 10 dígitos")
    private String telefono;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String direccion;

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "Formato de correo no válido")
    private String correo;

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
}
