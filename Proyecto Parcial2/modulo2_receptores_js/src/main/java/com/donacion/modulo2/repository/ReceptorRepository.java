package com.donacion.modulo2.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.donacion.modulo2.entity.Receptor;

/**
 * ReceptorRepository:
 * Interfaz que extiende JpaRepository para realizar operaciones CRUD
 * sobre la entidad Receptor.
 */
@Repository
public interface ReceptorRepository extends JpaRepository<Receptor, UUID> {

    /**
     * Busca un receptor por su correo electrónico.
     *
     * @param correo el correo del receptor.
     * @return Optional con el receptor si existe.
     */
    Optional<Receptor> findByCorreo(String correo);

    /**
     * Verifica si ya existe un receptor con el correo especificado.
     *
     * @param correo el correo a verificar.
     * @return true si existe, false si no.
     */
    boolean existsByCorreo(String correo);

    /**
     * Busca un receptor por su cédula.
     *
     * @param cedula la cédula del receptor.
     * @return Optional con el receptor si existe.
     */
    Optional<Receptor> findByCedula(String cedula);

    /**
     * Verifica si ya existe un receptor con la cédula especificada.
     *
     * @param cedula la cédula a verificar.
     * @return true si existe, false si no.
     */
    boolean existsByCedula(String cedula);

    /**
     * Busca un receptor con todas sus relaciones (direcciones, solicitudes, historial).
     *
     * @param id el ID del receptor.
     * @return Optional con el receptor completo si existe.
     */
    @Query("SELECT r FROM Receptor r LEFT JOIN FETCH r.direcciones LEFT JOIN FETCH r.solicitudes LEFT JOIN FETCH r.historial WHERE r.idReceptor = :id")
    Optional<Receptor> findByIdWithRelations(@Param("id") UUID id);

    /**
     * Busca un receptor con sus direcciones únicamente.
     *
     * @param id el ID del receptor.
     * @return Optional con el receptor y sus direcciones si existe.
     */
    @Query("SELECT r FROM Receptor r LEFT JOIN FETCH r.direcciones WHERE r.idReceptor = :id")
    Optional<Receptor> findByIdWithDirecciones(@Param("id") UUID id);

    /**
     * Busca un receptor con sus solicitudes únicamente.
     *
     * @param id el ID del receptor.
     * @return Optional con el receptor y sus solicitudes si existe.
     */
    @Query("SELECT r FROM Receptor r LEFT JOIN FETCH r.solicitudes WHERE r.idReceptor = :id")
    Optional<Receptor> findByIdWithSolicitudes(@Param("id") UUID id);

}
