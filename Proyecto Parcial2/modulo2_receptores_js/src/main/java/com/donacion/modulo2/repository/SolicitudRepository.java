package com.donacion.modulo2.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.donacion.modulo2.entity.Solicitud;

/**
 * Repositorio para la entidad Solicitud.
 * Permite operaciones CRUD utilizando Spring Data JPA.
 */
@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, UUID> {

    /**
     * Busca solicitudes por estado.
     *
     * @param estado el estado de las solicitudes
     * @return lista de solicitudes con ese estado
     */
    List<Solicitud> findByEstado(String estado);

    /**
     * Busca solicitudes por receptor.
     *
     * @param receptorId el ID del receptor
     * @return lista de solicitudes del receptor
     */
    List<Solicitud> findByReceptor_IdReceptor(UUID receptorId);

    /**
     * Busca solicitudes por tipo de donación.
     *
     * @param tipoDonacion el tipo de donación
     * @return lista de solicitudes de ese tipo
     */
    List<Solicitud> findByTipoDonacion(String tipoDonacion);

    /**
     * Busca solicitudes en un rango de fechas.
     *
     * @param fechaInicio fecha de inicio
     * @param fechaFin fecha de fin
     * @return lista de solicitudes en ese rango
     */
    List<Solicitud> findByFechaSolicitudBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    /**
     * Busca una solicitud con sus entregas.
     *
     * @param id el ID de la solicitud
     * @return Optional con la solicitud y sus entregas
     */
    @Query("SELECT s FROM Solicitud s LEFT JOIN FETCH s.entregas WHERE s.idSolicitud = :id")
    Optional<Solicitud> findByIdWithEntregas(@Param("id") UUID id);

    /**
     * Busca una solicitud con su receptor.
     *
     * @param id el ID de la solicitud
     * @return Optional con la solicitud y su receptor
     */
    @Query("SELECT s FROM Solicitud s LEFT JOIN FETCH s.receptor WHERE s.idSolicitud = :id")
    Optional<Solicitud> findByIdWithReceptor(@Param("id") UUID id);

    /**
     * Busca solicitudes con relaciones completas.
     *
     * @param id el ID de la solicitud
     * @return Optional con la solicitud completa
     */
    @Query("SELECT s FROM Solicitud s LEFT JOIN FETCH s.receptor LEFT JOIN FETCH s.entregas WHERE s.idSolicitud = :id")
    Optional<Solicitud> findByIdWithRelations(@Param("id") UUID id);

    /**
     * Cuenta solicitudes por estado.
     *
     * @param estado el estado a contar
     * @return número de solicitudes con ese estado
     */
    long countByEstado(String estado);

    /**
     * Busca solicitudes por receptor y estado.
     *
     * @param receptorId el ID del receptor
     * @param estado el estado de las solicitudes
     * @return lista de solicitudes filtradas
     */
    List<Solicitud> findByReceptor_IdReceptorAndEstado(UUID receptorId, String estado);
}
