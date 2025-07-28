package com.donacion.modulo2.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.donacion.modulo2.dto.SolicitudDTO;

/**
 * SolicitudService
 *
 * Interfaz que define las operaciones de negocio para la entidad Solicitud.
 * Será implementada en la clase SolicitudServiceImpl.
 */
public interface SolicitudService {

    /**
     * Crea una nueva solicitud asociada a un receptor.
     *
     * @param solicitudDTO Objeto con los datos de la solicitud.
     * @return SolicitudDTO creado.
     */
    SolicitudDTO crearSolicitud(SolicitudDTO solicitudDTO);

    /**
     * Retorna todas las solicitudes existentes.
     *
     * @return Lista de SolicitudDTO.
     */
    List<SolicitudDTO> obtenerTodas();

    /**
     * Obtiene una solicitud por su ID.
     *
     * @param id UUID de la solicitud.
     * @return SolicitudDTO correspondiente.
     */
    SolicitudDTO obtenerPorId(UUID id);

    /**
     * Actualiza una solicitud existente.
     *
     * @param id UUID de la solicitud a actualizar.
     * @param solicitudDTO Datos actualizados.
     * @return SolicitudDTO actualizado.
     */
    SolicitudDTO actualizarSolicitud(UUID id, SolicitudDTO solicitudDTO);

    /**
     * Elimina una solicitud por su ID.
     *
     * @param id UUID de la solicitud a eliminar.
     */
    void eliminarSolicitud(UUID id);

    /**
     * Busca solicitudes por estado.
     *
     * @param estado el estado a buscar
     * @return lista de solicitudes con ese estado
     */
    List<SolicitudDTO> obtenerPorEstado(String estado);

    /**
     * Busca solicitudes por receptor.
     *
     * @param receptorId el ID del receptor
     * @return lista de solicitudes del receptor
     */
    List<SolicitudDTO> obtenerPorReceptor(UUID receptorId);

    /**
     * Busca solicitudes por tipo de donación.
     *
     * @param tipoDonacion el tipo de donación
     * @return lista de solicitudes de ese tipo
     */
    List<SolicitudDTO> obtenerPorTipoDonacion(String tipoDonacion);

    /**
     * Busca solicitudes en un rango de fechas.
     *
     * @param fechaInicio fecha de inicio
     * @param fechaFin fecha de fin
     * @return lista de solicitudes en ese rango
     */
    List<SolicitudDTO> obtenerPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    /**
     * Cambia el estado de una solicitud.
     *
     * @param id el ID de la solicitud
     * @param nuevoEstado el nuevo estado
     * @return solicitud actualizada
     */
    SolicitudDTO cambiarEstado(UUID id, String nuevoEstado);

    /**
     * Cuenta solicitudes por estado.
     *
     * @param estado el estado a contar
     * @return número de solicitudes
     */
    long contarPorEstado(String estado);

    /**
     * Busca solicitudes por receptor y estado.
     *
     * @param receptorId el ID del receptor
     * @param estado el estado
     * @return lista de solicitudes filtradas
     */
    List<SolicitudDTO> obtenerPorReceptorYEstado(UUID receptorId, String estado);
}
