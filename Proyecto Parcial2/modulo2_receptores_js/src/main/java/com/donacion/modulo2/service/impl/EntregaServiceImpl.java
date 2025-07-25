package com.donacion.modulo2.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donacion.modulo2.dto.EntregaDTO;
import com.donacion.modulo2.entity.Entrega;
import com.donacion.modulo2.entity.Solicitud;
import com.donacion.modulo2.exception.RecursoNoEncontradoException;
import com.donacion.modulo2.repository.EntregaRepository;
import com.donacion.modulo2.repository.SolicitudRepository;
import com.donacion.modulo2.service.EntregaService;

import jakarta.transaction.Transactional;

/**
 * EntregaServiceImpl:
 * Implementación del servicio de entrega de víveres.
 */
@Service
public class EntregaServiceImpl implements EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Override
    @Transactional
    public EntregaDTO crearEntrega(EntregaDTO dto) {
        Solicitud solicitud = solicitudRepository.findById(dto.getIdSolicitud())
                .orElseThrow(() -> new RecursoNoEncontradoException("Solicitud no encontrada con ID: " + dto.getIdSolicitud()));

        Entrega entrega = new Entrega();
        entrega.setFechaEntrega(dto.getFechaEntrega());
        entrega.setObservacion(dto.getObservacion());
        entrega.setSolicitud(solicitud);
        entrega.setIdDonacion(dto.getIdDonacion()); // Guardamos solo el UUID

        Entrega guardada = entregaRepository.save(entrega);
        return mapToDTO(guardada);
    }

    @Override
    public List<EntregaDTO> obtenerTodas() {
        return entregaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EntregaDTO obtenerPorId(UUID id) {
        Entrega entrega = entregaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Entrega no encontrada con ID: " + id));
        return mapToDTO(entrega);
    }

    @Override
    @Transactional
    public EntregaDTO actualizarEntrega(UUID id, EntregaDTO dto) {
        Entrega entrega = entregaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Entrega no encontrada con ID: " + id));

        Solicitud solicitud = solicitudRepository.findById(dto.getIdSolicitud())
                .orElseThrow(() -> new RecursoNoEncontradoException("Solicitud no encontrada con ID: " + dto.getIdSolicitud()));

        entrega.setFechaEntrega(dto.getFechaEntrega());
        entrega.setObservacion(dto.getObservacion());
        entrega.setSolicitud(solicitud);
        entrega.setIdDonacion(dto.getIdDonacion());

        Entrega actualizada = entregaRepository.save(entrega);
        return mapToDTO(actualizada);
    }

    @Override
    @Transactional
    public void eliminarEntrega(UUID id) {
        Entrega entrega = entregaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Entrega no encontrada con ID: " + id));
        entregaRepository.delete(entrega);
    }

    /**
     * Convierte la entidad Entrega a su DTO.
     */
    private EntregaDTO mapToDTO(Entrega entrega) {
        EntregaDTO dto = new EntregaDTO();
        dto.setIdEntrega(entrega.getIdEntrega());
        dto.setFechaEntrega(entrega.getFechaEntrega());
        dto.setObservacion(entrega.getObservacion());
        dto.setIdSolicitud(entrega.getSolicitud().getIdSolicitud());
        dto.setIdDonacion(entrega.getIdDonacion());
        return dto;
    }
}
