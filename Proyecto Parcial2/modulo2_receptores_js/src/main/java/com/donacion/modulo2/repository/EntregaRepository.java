package com.donacion.modulo2.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.donacion.modulo2.entity.Entrega;

/**
 * EntregaRepository:
 * Repositorio JPA para la entidad Entrega.
 */
@Repository
public interface EntregaRepository extends JpaRepository<Entrega, UUID> {

    /**
     * Busca entregas por solicitud.
     *
     * @param solicitudId el ID de la solicitud
     * @return lista de entregas de esa solicitud
     */
    List<Entrega> findBySolicitud_IdSolicitud(UUID solicitudId);

    /**
     * Busca entregas por ID de donación.
     *
     * @param idDonacion el ID de la donación
     * @return lista de entregas de esa donación
     */
    List<Entrega> findByIdDonacion(UUID idDonacion);

    /**
     * Busca entregas por estado.
     *
     * @param estadoEntrega el estado de entrega
     * @return lista de entregas con ese estado
     */
    List<Entrega> findByEstadoEntrega(String estadoEntrega);

    /**
     * Busca entregas en un rango de fechas.
     *
     * @param fechaInicio fecha de inicio
     * @param fechaFin fecha de fin
     * @return lista de entregas en ese rango
     */
    List<Entrega> findByFechaEntregaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    /**
     * Busca una entrega con su solicitud.
     *
     * @param id el ID de la entrega
     * @return Optional con la entrega y su solicitud
     */
    @Query("SELECT e FROM Entrega e LEFT JOIN FETCH e.solicitud WHERE e.idEntrega = :id")
    Optional<Entrega> findByIdWithSolicitud(@Param("id") UUID id);

    /**
     * Busca una entrega con su solicitud y receptor.
     *
     * @param id el ID de la entrega
     * @return Optional con la entrega completa
     */
    @Query("SELECT e FROM Entrega e LEFT JOIN FETCH e.solicitud s LEFT JOIN FETCH s.receptor WHERE e.idEntrega = :id")
    Optional<Entrega> findByIdWithRelations(@Param("id") UUID id);

    /**
     * Cuenta entregas por estado.
     *
     * @param estadoEntrega el estado a contar
     * @return número de entregas con ese estado
     */
    long countByEstadoEntrega(String estadoEntrega);

    /**
     * Busca entregas por receptor (a través de solicitud).
     *
     * @param receptorId el ID del receptor
     * @return lista de entregas del receptor
     */
    @Query("SELECT e FROM Entrega e JOIN e.solicitud s WHERE s.receptor.idReceptor = :receptorId")
    List<Entrega> findByReceptorId(@Param("receptorId") UUID receptorId);

    /**
     * Busca la última entrega de una solicitud.
     *
     * @param solicitudId el ID de la solicitud
     * @return Optional con la última entrega
     */
    @Query("SELECT e FROM Entrega e WHERE e.solicitud.idSolicitud = :solicitudId ORDER BY e.fechaEntrega DESC LIMIT 1")
    Optional<Entrega> findUltimaEntregaBySolicitud(@Param("solicitudId") UUID solicitudId);

    /**
     * Busca entregas por solicitud y estado.
     *
     * @param solicitudId el ID de la solicitud
     * @param estadoEntrega el estado de entrega
     * @return lista de entregas filtradas
     */
    List<Entrega> findBySolicitud_IdSolicitudAndEstadoEntrega(UUID solicitudId, String estadoEntrega);

    /**
     * Obtiene estadísticas de entregas por fecha.
     *
     * @param fecha la fecha para obtener estadísticas
     * @return número de entregas en esa fecha
     */
    @Query("SELECT COUNT(e) FROM Entrega e WHERE DATE(e.fechaEntrega) = DATE(:fecha)")
    long countByFechaEntrega(@Param("fecha") LocalDateTime fecha);
}
