package com.modulo2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "receptor")
public class Receptor {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String nombre;
private String cedula;
private String telefono;
private String direccion;
private String correo;

public Receptor() {}

public Receptor(Long id, String nombre, String cedula, String telefono, String direccion, String correo) {
    this.id = id;
    this.nombre = nombre;
    this.cedula = cedula;
    this.telefono = telefono;
    this.direccion = direccion;
    this.correo = correo;
}

public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

public String getNombre() { return nombre; }
public void setNombre(String nombre) { this.nombre = nombre; }

public String getCedula() { return cedula; }
public void setCedula(String cedula) { this.cedula = cedula; }

public String getTelefono() { return telefono; }
public void setTelefono(String telefono) { this.telefono = telefono; }

public String getDireccion() { return direccion; }
public void setDireccion(String direccion) { this.direccion = direccion; }

public String getCorreo() { return correo; }
public void setCorreo(String correo) { this.correo = correo; }
}