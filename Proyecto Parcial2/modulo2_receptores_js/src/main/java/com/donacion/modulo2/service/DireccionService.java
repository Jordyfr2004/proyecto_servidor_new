package com.donacion.modulo2.service;

import java.util.List;
import java.util.UUID;

import com.donacion.modulo2.dto.DireccionDTO;

/**
 * DireccionService:
 * Interface de servicio para operaciones de direcciones.
 */
public interface DireccionService {

    /**
     * Crea una nueva dirección.
     *
     * @param dto datos de la dirección
     * @return dirección creada
     */
    DireccionDTO crearDireccion(DireccionDTO dto);

    /**
     * Obtiene todas las direcciones.
     *
     * @return lista de todas las direcciones
     */
    List<DireccionDTO> obtenerTodas();

    /**
     * Obtiene una dirección por su ID.
     *
     * @param id ID de la dirección
     * @return dirección encontrada
     */
    DireccionDTO obtenerPorId(UUID id);

    /**
     * Actualiza una dirección existente.
     *
     * @param id  ID de la dirección
     * @param dto nuevos datos de la dirección
     * @return dirección actualizada
     */
    DireccionDTO actualizarDireccion(UUID id, DireccionDTO dto);

    /**
     * Elimina una dirección.
     *
     * @param id ID de la dirección
     */
    void eliminarDireccion(UUID id);

    /**
     * Obtiene todas las direcciones de un receptor.
     *
     * @param receptorId ID del receptor
     * @return lista de direcciones del receptor
     */
    List<DireccionDTO> obtenerDireccionesPorReceptor(UUID receptorId);

    /**
     * Obtiene direcciones por ciudad.
     *
     * @param ciudad nombre de la ciudad
     * @return lista de direcciones en la ciudad
     */
    List<DireccionDTO> obtenerDireccionesPorCiudad(String ciudad);

    /**
     * Obtiene direcciones por provincia.
     *
     * @param provincia nombre de la provincia
     * @return lista de direcciones en la provincia
     */
    List<DireccionDTO> obtenerDireccionesPorProvincia(String provincia);

    /**
     * Obtiene direcciones por ciudad y provincia.
     *
     * @param ciudad    nombre de la ciudad
     * @param provincia nombre de la provincia
     * @return lista de direcciones
     */
    List<DireccionDTO> obtenerDireccionesPorCiudadYProvincia(String ciudad, String provincia);

    /**
     * Obtiene la dirección principal de un receptor.
     *
     * @param receptorId ID del receptor
     * @return dirección principal si existe, null si no
     */
    DireccionDTO obtenerDireccionPrincipal(UUID receptorId);

    /**
     * Marca una dirección como principal para un receptor.
     *
     * @param direccionId ID de la dirección
     * @param receptorId  ID del receptor
     * @return dirección actualizada
     */
    DireccionDTO marcarComoPrincipal(UUID direccionId, UUID receptorId);

    /**
     * Obtiene direcciones que tengan coordenadas geográficas.
     *
     * @return lista de direcciones con coordenadas
     */
    List<DireccionDTO> obtenerDireccionesConCoordenadas();

    /**
     * Busca direcciones por texto.
     *
     * @param texto texto a buscar
     * @return lista de direcciones que coinciden
     */
    List<DireccionDTO> buscarDireccionesPorTexto(String texto);

    /**
     * Actualiza las coordenadas geográficas de una dirección.
     *
     * @param direccionId ID de la dirección
     * @param latitud     latitud
     * @param longitud    longitud
     * @return dirección actualizada
     */
    DireccionDTO actualizarCoordenadas(UUID direccionId, Double latitud, Double longitud);

    /**
     * Cuenta el número de direcciones de un receptor.
     *
     * @param receptorId ID del receptor
     * @return número de direcciones
     */
    long contarDireccionesPorReceptor(UUID receptorId);
}
