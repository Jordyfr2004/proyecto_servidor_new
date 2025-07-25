package com.donacion.modulo2.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Entidad Direccion:
 * Representa las direcciones asociadas a los receptores de víveres.
 */
@Entity
@Table(name = "direcciones",
    indexes = {
        @Index(name = "idx_direccion_receptor", columnList = "id_receptor"),
        @Index(name = "idx_direccion_ciudad", columnList = "ciudad"),
        @Index(name = "idx_direccion_provincia", columnList = "provincia")
})
public class Direccion {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_direccion", updatable = false, nullable = false)
    private UUID idDireccion;

    @NotBlank(message = "La calle es obligatoria")
    @Size(max = 200, message = "La calle no puede exceder 200 caracteres")
    @Column(name = "calle", nullable = false, length = 200)
    private String calle;

    @NotBlank(message = "La ciudad es obligatoria")
    @Size(max = 100, message = "La ciudad no puede exceder 100 caracteres")
    @Column(name = "ciudad", nullable = false, length = 100)
    private String ciudad;

    @NotBlank(message = "La provincia es obligatoria")
    @Size(max = 100, message = "La provincia no puede exceder 100 caracteres")
    @Column(name = "provincia", nullable = false, length = 100)
    private String provincia;

    @NotBlank(message = "La referencia es obligatoria")
    @Size(max = 500, message = "La referencia no puede exceder 500 caracteres")
    @Column(name = "referencia", nullable = false, length = 500)
    private String referencia;

    @Size(max = 20, message = "El código postal no puede exceder 20 caracteres")
    @Column(name = "codigo_postal", length = 20)
    private String codigoPostal;

    @Column(name = "latitud")
    private Double latitud;

    @Column(name = "longitud")
    private Double longitud;

    @Column(name = "es_principal")
    private Boolean esPrincipal = false;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    // Relación con receptor (ManyToOne)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_receptor", nullable = false)
    private Receptor receptor;

    // Constructores
    public Direccion() {}

    public Direccion(String calle, String ciudad, String provincia, String referencia, Receptor receptor) {
        this.calle = calle;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.referencia = referencia;
        this.receptor = receptor;
    }

    public Direccion(String calle, String ciudad, String provincia, String referencia, 
            String codigoPostal, Receptor receptor) {
        this(calle, ciudad, provincia, referencia, receptor);
        this.codigoPostal = codigoPostal;
    }

    // Métodos que se ejecutan antes de persistir y actualizar
    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }

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

    public Receptor getReceptor() {
        return receptor;
    }

    public void setReceptor(Receptor receptor) {
        this.receptor = receptor;
    }

    // Métodos auxiliares

    /**
     * Obtiene la dirección completa como string.
     *
     * @return dirección completa
     */
    public String getDireccionCompleta() {
        StringBuilder sb = new StringBuilder();
        sb.append(calle).append(", ");
        sb.append(ciudad).append(", ");
        sb.append(provincia);
        if (codigoPostal != null && !codigoPostal.isEmpty()) {
            sb.append(" ").append(codigoPostal);
        }
        return sb.toString();
    }

    /**
     * Verifica si tiene coordenadas geográficas.
     *
     * @return true si tiene latitud y longitud
     */
    public boolean tieneCoordenadas() {
        return latitud != null && longitud != null;
    }

    /**
     * Marca esta dirección como principal.
     */
    public void marcarComoPrincipal() {
        this.esPrincipal = true;
    }

    /**
     * Marca esta dirección como no principal.
     */
    public void desmarcarComoPrincipal() {
        this.esPrincipal = false;
    }

    /**
     * Establece las coordenadas geográficas.
     *
     * @param latitud  latitud
     * @param longitud longitud
     */
    public void establecerCoordenadas(Double latitud, Double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
