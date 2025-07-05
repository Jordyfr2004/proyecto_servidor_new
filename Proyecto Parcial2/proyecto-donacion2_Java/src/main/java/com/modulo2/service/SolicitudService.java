package com.modulo2.service;

import java.util.List;

import com.modulo2.model.Solicitud;

public interface SolicitudService {
    List<Solicitud> obtenerTodas();
    Solicitud obtenerPorId(Long id);
    Solicitud guardar(Solicitud solicitud);
    void eliminar(Long id);
}
