package com.donacion.modulo2.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donacion.modulo2.dto.HistorialReceptorDTO;
import com.donacion.modulo2.entity.HistorialReceptor;
import com.donacion.modulo2.entity.Receptor;
import com.donacion.modulo2.exception.RecursoNoEncontradoException;
import com.donacion.modulo2.repository.HistorialReceptorRepository;
import com.donacion.modulo2.repository.ReceptorRepository;
import com.donacion.modulo2.service.HistorialReceptorService;

import jakarta.transaction.Transactional;

@Service
public class HistorialReceptorServiceImpl implements HistorialReceptorService {

    @Autowired
    private HistorialReceptorRepository historialRepository;

    @Autowired
    private ReceptorRepository receptorRepository;

    @Override
    @Transactional
    public HistorialReceptorDTO crearHistorial(HistorialReceptorDTO dto) {
        Receptor receptor = receptorRepository.findById(dto.getIdReceptor())
                .orElseThrow(() -> new RecursoNoEncontradoException("Receptor no encontrado con ID: " + dto.getIdReceptor()));

        HistorialReceptor historial = new HistorialReceptor();
        historial.setFechaEntrega(dto.getFechaEntrega());
        historial.setObservacion(dto.getObservacion());
        historial.setReceptor(receptor);

        HistorialReceptor guardado = historialRepository.save(historial);
        return mapToDTO(guardado);
    }

    @Override
    public List<HistorialReceptorDTO> obtenerTodos() {
        return historialRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HistorialReceptorDTO obtenerPorId(UUID id) {
        HistorialReceptor historial = historialRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Historial no encontrado con ID: " + id));
        return mapToDTO(historial);
    }

    @Override
    @Transactional
    public HistorialReceptorDTO actualizarHistorial(UUID id, HistorialReceptorDTO dto) {
        HistorialReceptor historial = historialRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Historial no encontrado con ID: " + id));

        Receptor receptor = receptorRepository.findById(dto.getIdReceptor())
                .orElseThrow(() -> new RecursoNoEncontradoException("Receptor no encontrado con ID: " + dto.getIdReceptor()));

        historial.setFechaEntrega(dto.getFechaEntrega());
        historial.setObservacion(dto.getObservacion());
        historial.setReceptor(receptor);

        HistorialReceptor actualizado = historialRepository.save(historial);
        return mapToDTO(actualizado);
    }

    @Override
    @Transactional
    public void eliminarHistorial(UUID id) {
        HistorialReceptor historial = historialRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Historial no encontrado con ID: " + id));
        historialRepository.delete(historial);
    }

    // Método auxiliar para convertir de entidad a DTO
    private HistorialReceptorDTO mapToDTO(HistorialReceptor historial) {
        HistorialReceptorDTO dto = new HistorialReceptorDTO();
        dto.setIdHistorial(historial.getIdHistorial());
        dto.setFechaEntrega(historial.getFechaEntrega());
        dto.setObservacion(historial.getObservacion());
        dto.setIdReceptor(historial.getReceptor().getIdReceptor());
        return dto;
    }
}
