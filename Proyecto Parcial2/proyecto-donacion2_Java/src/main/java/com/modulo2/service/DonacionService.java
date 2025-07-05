package com.modulo2.service;

import com.modulo2.model.Donacion;

public interface DonacionService {
    Donacion obtenerPorId(Long id);
    Donacion guardar(Donacion donacion);
}
