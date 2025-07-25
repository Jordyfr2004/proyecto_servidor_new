package com.donacion.modulo2.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.donacion.modulo2.entity.HistorialReceptor;
import com.donacion.modulo2.entity.HistorialReceptor.Estado;
import com.donacion.modulo2.entity.HistorialReceptor.TipoEvento;

/**
 * HistorialReceptorRepository:
 * Repositorio para gestionar operaciones de base de datos relacionadas con el historial de receptores.
 */
@Repository
public interface HistorialReceptorRepository extends JpaRepository<HistorialReceptor, UUID> {

    /**
     * Busca todo el historial de un receptor específico.
     *
     * @param receptorId ID del receptor
     * @return lista de historiales del receptor
     */
    List<HistorialReceptor> findByReceptorIdReceptorOrderByFechaEntregaDesc(UUID receptorId);

    /**
     * Busca historiales por tipo de evento.
     *
     * @param tipoEvento tipo de evento
     * @return lista de historiales del tipo especificado
     */
    List<HistorialReceptor> findByTipoEvento(TipoEvento tipoEvento);

    /**
     * Busca historiales por estado.
     *
     * @param estado estado del historial
     * @return lista de historiales con el estado especificado
     */
    List<HistorialReceptor> findByEstado(Estado estado);

    /**
     * Busca historiales por receptor y tipo de evento.
     *
     * @param receptorId ID del receptor
     * @param tipoEvento tipo de evento
     * @return lista de historiales
     */
    List<HistorialReceptor> findByReceptorIdReceptorAndTipoEvento(UUID receptorId, TipoEvento tipoEvento);

    /**
     * Busca historiales por receptor y estado.
     *
     * @param receptorId ID del receptor
     * @param estado     estado del historial
     * @return lista de historiales
     */
    List<HistorialReceptor> findByReceptorIdReceptorAndEstado(UUID receptorId, Estado estado);

    /**
     * Busca historiales en un rango de fechas.
     *
     * @param fechaInicio fecha de inicio
     * @param fechaFin    fecha de fin
     * @return lista de historiales en el rango
     */
    List<HistorialReceptor> findByFechaEntregaBetweenOrderByFechaEntregaDesc(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    /**
     * Busca historiales de un receptor en un rango de fechas.
     *
     * @param receptorId  ID del receptor
     * @param fechaInicio fecha de inicio
     * @param fechaFin    fecha de fin
     * @return lista de historiales
     */
    List<HistorialReceptor> findByReceptorIdReceptorAndFechaEntregaBetweenOrderByFechaEntregaDesc(
        UUID receptorId, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    /**
     * Cuenta el número de historiales de un receptor.
     *
     * @param receptorId ID del receptor
     * @return número de historiales
     */
    long countByReceptorIdReceptor(UUID receptorId);

    /**
     * Cuenta historiales por tipo de evento.
     *
     * @param tipoEvento tipo de evento
     * @return número de historiales
     */
    long countByTipoEvento(TipoEvento tipoEvento);

    /**
     * Obtiene el último historial de un receptor.
     *
     * @param receptorId ID del receptor
     * @return historial más reciente
     */
    @Query("SELECT h FROM HistorialReceptor h WHERE h.receptor.idReceptor = :receptorId ORDER BY h.fechaEntrega DESC LIMIT 1")
    HistorialReceptor findUltimoHistorialPorReceptor(@Param("receptorId") UUID receptorId);

    /**
     * Busca historiales por texto en observación o detalle adicional.
     *
     * @param texto texto a buscar
     * @return lista de historiales que coinciden
     */
    @Query("SELECT h FROM HistorialReceptor h WHERE " +
        "LOWER(h.observacion) LIKE LOWER(CONCAT('%', :texto, '%')) OR " +
        "LOWER(h.detalleAdicional) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<HistorialReceptor> buscarPorTexto(@Param("texto") String texto);

    /**
     * Obtiene estadísticas de entregas por receptor.
     *
     * @param receptorId ID del receptor
     * @return cantidad total entregada
     */
    @Query("SELECT COALESCE(SUM(h.cantidadEntregada), 0) FROM HistorialReceptor h " +
        "WHERE h.receptor.idReceptor = :receptorId AND h.tipoEvento = 'ENTREGA' AND h.estado = 'COMPLETADO'")
    Double obtenerTotalEntregadoPorReceptor(@Param("receptorId") UUID receptorId);

    /**
     * Obtiene el valor total estimado de entregas por receptor.
     *
     * @param receptorId ID del receptor
     * @return valor total estimado
     */
    @Query("SELECT COALESCE(SUM(h.valorEstimado), 0) FROM HistorialReceptor h " +
        "WHERE h.receptor.idReceptor = :receptorId AND h.tipoEvento = 'ENTREGA' AND h.estado = 'COMPLETADO'")
    Double obtenerValorTotalPorReceptor(@Param("receptorId") UUID receptorId);

    /**
     * Busca historiales por usuario registrador.
     *
     * @param usuario usuario registrador
     * @return lista de historiales
     */
    List<HistorialReceptor> findByUsuarioRegistradorIgnoreCase(String usuario);

    /**
     * Obtiene historiales recientes (últimos 30 días).
     *
     * @return lista de historiales recientes
     */
    @Query("SELECT h FROM HistorialReceptor h WHERE h.fechaEntrega >= :fechaInicio ORDER BY h.fechaEntrega DESC")
    List<HistorialReceptor> findHistorialesRecientes(@Param("fechaInicio") LocalDateTime fechaInicio);
}
