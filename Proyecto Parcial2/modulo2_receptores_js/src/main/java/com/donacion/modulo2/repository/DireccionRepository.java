package com.donacion.modulo2.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.donacion.modulo2.entity.Direccion;

/**
 * DireccionRepository:
 * Repositorio para gestionar operaciones de base de datos relacionadas con direcciones.
 */
@Repository
public interface DireccionRepository extends JpaRepository<Direccion, UUID> {
    
    /**
     * Busca todas las direcciones de un receptor específico.
     *
     * @param receptorId ID del receptor
     * @return lista de direcciones del receptor
     */
    List<Direccion> findByReceptorIdReceptor(UUID receptorId);

    /**
     * Busca direcciones por ciudad.
     *
     * @param ciudad nombre de la ciudad
     * @return lista de direcciones en la ciudad
     */
    List<Direccion> findByCiudadIgnoreCase(String ciudad);

    /**
     * Busca direcciones por provincia.
     *
     * @param provincia nombre de la provincia
     * @return lista de direcciones en la provincia
     */
    List<Direccion> findByProvinciaIgnoreCase(String provincia);

    /**
     * Busca direcciones por ciudad y provincia.
     *
     * @param ciudad    nombre de la ciudad
     * @param provincia nombre de la provincia
     * @return lista de direcciones
     */
    List<Direccion> findByCiudadIgnoreCaseAndProvinciaIgnoreCase(String ciudad, String provincia);

    /**
     * Busca la dirección principal de un receptor.
     *
     * @param receptorId ID del receptor
     * @return dirección principal si existe
     */
    Optional<Direccion> findByReceptorIdReceptorAndEsPrincipalTrue(UUID receptorId);

    /**
     * Busca direcciones que tengan coordenadas geográficas.
     *
     * @return lista de direcciones con coordenadas
     */
    @Query("SELECT d FROM Direccion d WHERE d.latitud IS NOT NULL AND d.longitud IS NOT NULL")
    List<Direccion> findDireccionesConCoordenadas();

    /**
     * Busca direcciones por código postal.
     *
     * @param codigoPostal código postal
     * @return lista de direcciones
     */
    List<Direccion> findByCodigoPostal(String codigoPostal);

    /**
     * Cuenta el número de direcciones por receptor.
     *
     * @param receptorId ID del receptor
     * @return número de direcciones
     */
    @Query("SELECT COUNT(d) FROM Direccion d WHERE d.receptor.idReceptor = :receptorId")
    long countByReceptorId(@Param("receptorId") UUID receptorId);

    /**
     * Busca direcciones por texto en cualquier campo de dirección.
     *
     * @param texto texto a buscar
     * @return lista de direcciones que coinciden
     */
    @Query("SELECT d FROM Direccion d WHERE " +
        "LOWER(d.calle) LIKE LOWER(CONCAT('%', :texto, '%')) OR " +
        "LOWER(d.ciudad) LIKE LOWER(CONCAT('%', :texto, '%')) OR " +
        "LOWER(d.provincia) LIKE LOWER(CONCAT('%', :texto, '%')) OR " +
        "LOWER(d.referencia) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<Direccion> buscarPorTexto(@Param("texto") String texto);

    /**
     * Busca direcciones dentro de un área geográfica.
     *
     * @param latitudMin latitud mínima
     * @param latitudMax latitud máxima
     * @param longitudMin longitud mínima
     * @param longitudMax longitud máxima
     * @return lista de direcciones en el área
     */
    @Query("SELECT d FROM Direccion d WHERE " +
        "d.latitud BETWEEN :latitudMin AND :latitudMax AND " +
        "d.longitud BETWEEN :longitudMin AND :longitudMax")
    List<Direccion> findByAreaGeografica(
        @Param("latitudMin") Double latitudMin,
        @Param("latitudMax") Double latitudMax,
        @Param("longitudMin") Double longitudMin,
        @Param("longitudMax") Double longitudMax
    );
}
