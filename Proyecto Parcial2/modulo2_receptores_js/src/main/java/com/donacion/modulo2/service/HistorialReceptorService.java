package com.donacion.modulo2.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.donacion.modulo2.dto.HistorialReceptorDTO;
import com.donacion.modulo2.entity.HistorialReceptor.Estado;
import com.donacion.modulo2.entity.HistorialReceptor.TipoEvento;

/**
 * HistorialReceptorService:
 * Interface de servicio para operaciones del historial de receptores.
 */
public interface HistorialReceptorService {

    /**
     * Crea un nuevo registro de historial.
     *
     * @param dto datos del historial
     * @return historial creado
     */
    HistorialReceptorDTO crearHistorial(HistorialReceptorDTO dto);

    /**
     * Obtiene todos los registros de historial.
     *
     * @return lista de todos los historiales
     */
    List<HistorialReceptorDTO> obtenerTodos();

    /**
     * Obtiene un historial por su ID.
     *
     * @param id ID del historial
     * @return historial encontrado
     */
    HistorialReceptorDTO obtenerPorId(UUID id);

    /**
     * Actualiza un historial existente.
     *
     * @param id  ID del historial
     * @param dto nuevos datos del historial
     * @return historial actualizado
     */
    HistorialReceptorDTO actualizarHistorial(UUID id, HistorialReceptorDTO dto);

    /**
     * Elimina un historial.
     *
     * @param id ID del historial
     */
    void eliminarHistorial(UUID id);

    /**
     * Obtiene todo el historial de un receptor específico.
     *
     * @param receptorId ID del receptor
     * @return lista de historiales del receptor ordenada por fecha descendente
     */
    List<HistorialReceptorDTO> obtenerHistorialPorReceptor(UUID receptorId);

    /**
     * Obtiene historiales por tipo de evento.
     *
     * @param tipoEvento tipo de evento
     * @return lista de historiales del tipo especificado
     */
    List<HistorialReceptorDTO> obtenerHistorialPorTipoEvento(TipoEvento tipoEvento);

    /**
     * Obtiene historiales por estado.
     *
     * @param estado estado del historial
     * @return lista de historiales con el estado especificado
     */
    List<HistorialReceptorDTO> obtenerHistorialPorEstado(Estado estado);

    /**
     * Obtiene historiales por receptor y tipo de evento.
     *
     * @param receptorId ID del receptor
     * @param tipoEvento tipo de evento
     * @return lista de historiales
     */
    List<HistorialReceptorDTO> obtenerHistorialPorReceptorYTipoEvento(UUID receptorId, TipoEvento tipoEvento);

    /**
     * Obtiene historiales por receptor y estado.
     *
     * @param receptorId ID del receptor
     * @param estado     estado del historial
     * @return lista de historiales
     */
    List<HistorialReceptorDTO> obtenerHistorialPorReceptorYEstado(UUID receptorId, Estado estado);

    /**
     * Obtiene historiales en un rango de fechas.
     *
     * @param fechaInicio fecha de inicio
     * @param fechaFin    fecha de fin
     * @return lista de historiales en el rango
     */
    List<HistorialReceptorDTO> obtenerHistorialPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    /**
     * Obtiene historiales de un receptor en un rango de fechas.
     *
     * @param receptorId  ID del receptor
     * @param fechaInicio fecha de inicio
     * @param fechaFin    fecha de fin
     * @return lista de historiales
     */
    List<HistorialReceptorDTO> obtenerHistorialPorReceptorYRangoFechas(UUID receptorId, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    /**
     * Obtiene el último historial de un receptor.
     *
     * @param receptorId ID del receptor
     * @return historial más reciente
     */
    HistorialReceptorDTO obtenerUltimoHistorialPorReceptor(UUID receptorId);

    /**
     * Busca historiales por texto en observaciones.
     *
     * @param texto texto a buscar
     * @return lista de historiales que coinciden
     */
    List<HistorialReceptorDTO> buscarHistorialPorTexto(String texto);

    /**
     * Marca un historial como completado.
     *
     * @param historialId ID del historial
     * @return historial actualizado
     */
    HistorialReceptorDTO completarHistorial(UUID historialId);

    /**
     * Marca un historial como cancelado.
     *
     * @param historialId ID del historial
     * @return historial actualizado
     */
    HistorialReceptorDTO cancelarHistorial(UUID historialId);

    /**
     * Registra una nueva entrega en el historial.
     *
     * @param receptorId        ID del receptor
     * @param observacion       observación de la entrega
     * @param cantidadEntregada cantidad entregada
     * @param unidad            unidad de medida
     * @param valorEstimado     valor estimado
     * @param usuarioRegistrador usuario que registra
     * @return historial de entrega creado
     */
    HistorialReceptorDTO registrarEntrega(UUID receptorId, String observacion, Double cantidadEntregada,
                                    String unidad, Double valorEstimado, String usuarioRegistrador);

    /**
     * Cuenta el número de historiales de un receptor.
     *
     * @param receptorId ID del receptor
     * @return número de historiales
     */
    long contarHistorialPorReceptor(UUID receptorId);

    /**
     * Obtiene estadísticas de entregas por receptor.
     *
     * @param receptorId ID del receptor
     * @return cantidad total entregada
     */
    Double obtenerTotalEntregadoPorReceptor(UUID receptorId);

    /**
     * Obtiene el valor total estimado de entregas por receptor.
     *
     * @param receptorId ID del receptor
     * @return valor total estimado
     */
    Double obtenerValorTotalPorReceptor(UUID receptorId);

    /**
     * Obtiene historiales por usuario registrador.
     *
     * @param usuario usuario registrador
     * @return lista de historiales
     */
    List<HistorialReceptorDTO> obtenerHistorialPorUsuario(String usuario);

    /**
     * Obtiene historiales recientes (últimos 30 días).
     *
     * @return lista de historiales recientes
     */
    List<HistorialReceptorDTO> obtenerHistorialesRecientes();
}
