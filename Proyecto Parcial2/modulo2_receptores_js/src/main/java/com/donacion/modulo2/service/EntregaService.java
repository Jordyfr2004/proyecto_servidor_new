package com.donacion.modulo2.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.donacion.modulo2.dto.EntregaDTO;

/**
 * EntregaService:
 * Interfaz que define las operaciones de negocio para la entidad Entrega.
 */
public interface EntregaService {

    /**
     * Crea una nueva entrega asociada a una solicitud y donación.
     *
     * @param entregaDTO Objeto con los datos de la entrega.
     * @return EntregaDTO creado.
     */
    EntregaDTO crearEntrega(EntregaDTO entregaDTO);

    /**
     * Retorna todas las entregas existentes.
     *
     * @return Lista de EntregaDTO.
     */
    List<EntregaDTO> obtenerTodas();

    /**
     * Obtiene una entrega por su ID.
     *
     * @param id UUID de la entrega.
     * @return EntregaDTO correspondiente.
     */
    EntregaDTO obtenerPorId(UUID id);

    /**
     * Actualiza una entrega existente.
     *
     * @param id UUID de la entrega a actualizar.
     * @param entregaDTO Datos actualizados.
     * @return EntregaDTO actualizado.
     */
    EntregaDTO actualizarEntrega(UUID id, EntregaDTO entregaDTO);

    /**
     * Elimina una entrega por su ID.
     *
     * @param id UUID de la entrega a eliminar.
     */
    void eliminarEntrega(UUID id);

    /**
     * Busca entregas por solicitud.
     *
     * @param solicitudId el ID de la solicitud
     * @return lista de entregas de esa solicitud
     */
    List<EntregaDTO> obtenerPorSolicitud(UUID solicitudId);

    /**
     * Busca entregas por ID de donación.
     *
     * @param idDonacion el ID de la donación
     * @return lista de entregas de esa donación
     */
    List<EntregaDTO> obtenerPorDonacion(UUID idDonacion);

    /**
     * Busca entregas por estado.
     *
     * @param estadoEntrega el estado de entrega
     * @return lista de entregas con ese estado
     */
    List<EntregaDTO> obtenerPorEstado(String estadoEntrega);

    /**
     * Busca entregas en un rango de fechas.
     *
     * @param fechaInicio fecha de inicio
     * @param fechaFin fecha de fin
     * @return lista de entregas en ese rango
     */
    List<EntregaDTO> obtenerPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    /**
     * Busca entregas por receptor.
     *
     * @param receptorId el ID del receptor
     * @return lista de entregas del receptor
     */
    List<EntregaDTO> obtenerPorReceptor(UUID receptorId);

    /**
     * Obtiene la última entrega de una solicitud.
     *
     * @param solicitudId el ID de la solicitud
     * @return EntregaDTO de la última entrega
     */
    EntregaDTO obtenerUltimaEntregaDeSolicitud(UUID solicitudId);

    /**
     * Cambia el estado de una entrega.
     *
     * @param id el ID de la entrega
     * @param nuevoEstado el nuevo estado
     * @return entrega actualizada
     */
    EntregaDTO cambiarEstado(UUID id, String nuevoEstado);

    /**
     * Marca una entrega como completada.
     *
     * @param id el ID de la entrega
     * @return entrega actualizada
     */
    EntregaDTO completarEntrega(UUID id);

    /**
     * Cuenta entregas por estado.
     *
     * @param estadoEntrega el estado a contar
     * @return número de entregas
     */
    long contarPorEstado(String estadoEntrega);

    /**
     * Obtiene estadísticas de entregas por fecha.
     *
     * @param fecha la fecha para obtener estadísticas
     * @return número de entregas en esa fecha
     */
    long contarPorFecha(LocalDateTime fecha);
}
