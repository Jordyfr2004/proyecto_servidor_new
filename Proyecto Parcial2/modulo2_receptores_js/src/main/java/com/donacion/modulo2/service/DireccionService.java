package com.donacion.modulo2.service;

import java.util.List;
import java.util.UUID;

import com.donacion.modulo2.dto.DireccionDTO;

public interface DireccionService {

    DireccionDTO crearDireccion(DireccionDTO dto);

    List<DireccionDTO> obtenerTodas();

    DireccionDTO obtenerPorId(UUID id);

    DireccionDTO actualizarDireccion(UUID id, DireccionDTO dto);

    void eliminarDireccion(UUID id);
}
