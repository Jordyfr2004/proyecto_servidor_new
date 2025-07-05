package com.modulo2.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modulo2.model.HistorialReceptor;
import com.modulo2.repository.HistorialReceptorRepository;
import com.modulo2.service.HistorialReceptorService;

@Service
public class HistorialReceptorServiceImpl implements HistorialReceptorService {

@Autowired
private HistorialReceptorRepository historialRepository;

@Override
public List<HistorialReceptor> obtenerTodos() {
    return historialRepository.findAll();
}

@Override
public HistorialReceptor obtenerPorId(Long id) {
    return historialRepository.findById(id).orElse(null);
}

@Override
public HistorialReceptor guardar(HistorialReceptor historial) {
    return historialRepository.save(historial);
}

@Override
public void eliminar(Long id) {
    historialRepository.deleteById(id);
}

// Implementación del método de IA simulada
@Override
public String evaluarPrioridad(Long receptorId) {
List<HistorialReceptor> historiales = historialRepository.findAll().stream()
.filter(h -> h.getReceptor() != null && h.getReceptor().getId().equals(receptorId))
.collect(Collectors.toList());

if (historiales.isEmpty()) {
    return "Sin prioridad: No hay registros para este receptor.";
}

// Contar ocurrencias por descripción
long urgentes = historiales.stream()
    .filter(h -> h.getDescripcion() != null && h.getDescripcion().toLowerCase().contains("urgente"))
    .count();

long errores = historiales.stream()
    .filter(h -> h.getDescripcion() != null && h.getDescripcion().toLowerCase().contains("error"))
    .count();

if (urgentes >= 1 || errores >= 1) {
    return "Alta prioridad: Se encontraron registros urgentes o con errores.";
}

if (historiales.size() >= 3) {
    return "Prioridad media: Receptor con varios registros.";
}

return "Prioridad baja: Receptor con pocos registros sin indicios de urgencia.";
}
}
