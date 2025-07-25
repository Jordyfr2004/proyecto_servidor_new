package com.donacion.modulo2.service;

import java.util.List;
import java.util.UUID;

import com.donacion.modulo2.dto.HistorialReceptorDTO;

public interface HistorialReceptorService {

    HistorialReceptorDTO crearHistorial(HistorialReceptorDTO dto);

    List<HistorialReceptorDTO> obtenerTodos();

    HistorialReceptorDTO obtenerPorId(UUID id);

    HistorialReceptorDTO actualizarHistorial(UUID id, HistorialReceptorDTO dto);

    void eliminarHistorial(UUID id);
}
