package com.modulo2.service;

import java.util.List;

import com.modulo2.model.Receptor;

public interface ReceptorService {
List<Receptor> obtenerTodos();
Receptor obtenerPorId(Long id);
Receptor guardar(Receptor receptor);
void eliminar(Long id);
}