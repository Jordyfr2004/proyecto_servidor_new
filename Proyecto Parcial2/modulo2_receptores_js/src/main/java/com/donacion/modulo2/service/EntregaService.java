package com.donacion.modulo2.service;

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
}
