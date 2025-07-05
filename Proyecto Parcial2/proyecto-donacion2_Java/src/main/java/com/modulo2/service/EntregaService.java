package com.modulo2.service;

import java.util.List;

import com.modulo2.model.Entrega;

public interface EntregaService {
    List<Entrega> obtenerTodas();
    Entrega obtenerPorId(Long id);
    Entrega guardar(Entrega entrega);
    void eliminar(Long id);
}
