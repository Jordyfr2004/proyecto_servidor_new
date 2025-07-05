package com.modulo2.service;

import java.util.List;

import com.modulo2.model.Direccion;

public interface DireccionService {
List<Direccion> obtenerTodas();
Direccion obtenerPorId(Long id);
Direccion guardar(Direccion direccion);
void eliminar(Long id);
}