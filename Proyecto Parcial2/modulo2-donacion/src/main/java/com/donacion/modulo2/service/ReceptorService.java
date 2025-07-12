package com.donacion.modulo2.service;

import java.util.List;
import java.util.UUID;

import com.donacion.modulo2.dto.ReceptorDTO;

/**
 * ReceptorService:
 * Interfaz que define las operaciones del servicio para la entidad Receptor.
 * Estas operaciones son implementadas en ReceptorServiceImpl.
 */
public interface ReceptorService {

    /**
     * Crea un nuevo receptor.
     *
     * @param receptorDTO Datos del receptor a registrar.
     * @return ReceptorDTO del receptor guardado.
     */
    ReceptorDTO crearReceptor(ReceptorDTO receptorDTO);

    /**
     * Obtiene una lista de todos los receptores registrados.
     *
     * @return Lista de ReceptorDTO.
     */
    List<ReceptorDTO> obtenerTodos();

    /**
     * Busca un receptor por su ID.
     *
     * @param id UUID del receptor.
     * @return ReceptorDTO si existe.
     */
    ReceptorDTO obtenerPorId(UUID id);

    /**
     * Actualiza un receptor existente por su ID.
     *
     * @param id UUID del receptor a actualizar.
     * @param receptorDTO Datos nuevos del receptor.
     * @return ReceptorDTO actualizado.
     */
    ReceptorDTO actualizarReceptor(UUID id, ReceptorDTO receptorDTO);

    /**
     * Elimina un receptor por su ID.
     *
     * @param id UUID del receptor a eliminar.
     */
    void eliminarReceptor(UUID id);

    /**
     * Verifica si un correo ya está registrado.
     *
     * @param correo correo electrónico a verificar.
     * @return true si existe, false si no.
     */
    boolean existePorCorreo(String correo);
}
