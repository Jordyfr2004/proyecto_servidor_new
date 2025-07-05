package com.modulo2.service;

import java.util.List;

import com.modulo2.model.HistorialReceptor;

public interface HistorialReceptorService {
List<HistorialReceptor> obtenerTodos();
HistorialReceptor obtenerPorId(Long id);
HistorialReceptor guardar(HistorialReceptor historial);
void eliminar(Long id);

// Método adicional para IA simulada
String evaluarPrioridad(Long receptorId);
}