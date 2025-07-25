package com.donacion.modulo2.service;

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
}
