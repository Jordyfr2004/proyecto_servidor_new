package com.donacion.modulo2.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donacion.modulo2.dto.SolicitudDTO;
import com.donacion.modulo2.entity.Receptor;
import com.donacion.modulo2.entity.Solicitud;
import com.donacion.modulo2.exception.RecursoNoEncontradoException;
import com.donacion.modulo2.repository.ReceptorRepository;
import com.donacion.modulo2.repository.SolicitudRepository;
import com.donacion.modulo2.service.SolicitudService;

import jakarta.transaction.Transactional;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private ReceptorRepository receptorRepository;

    @Override
    @Transactional
    public SolicitudDTO crearSolicitud(SolicitudDTO dto) {
        Receptor receptor = receptorRepository.findById(dto.getIdReceptor())
                .orElseThrow(() -> new RecursoNoEncontradoException("Receptor no encontrado con ID: " + dto.getIdReceptor()));

        Solicitud solicitud = new Solicitud();
        solicitud.setTipoDonacion(dto.getTipoDonacion());
        solicitud.setDescripcion(dto.getDescripcion());
        solicitud.setFechaSolicitud(dto.getFechaSolicitud());
        solicitud.setEstado(dto.getEstado());
        solicitud.setReceptor(receptor);

        Solicitud guardada = solicitudRepository.save(solicitud);
        return mapToDTO(guardada);
    }

    @Override
    public List<SolicitudDTO> obtenerTodas() {
        return solicitudRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SolicitudDTO obtenerPorId(UUID id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Solicitud no encontrada con ID: " + id));
        return mapToDTO(solicitud);
    }

    @Override
    @Transactional
    public SolicitudDTO actualizarSolicitud(UUID id, SolicitudDTO dto) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Solicitud no encontrada con ID: " + id));

        Receptor receptor = receptorRepository.findById(dto.getIdReceptor())
                .orElseThrow(() -> new RecursoNoEncontradoException("Receptor no encontrado con ID: " + dto.getIdReceptor()));

        solicitud.setTipoDonacion(dto.getTipoDonacion());
        solicitud.setDescripcion(dto.getDescripcion());
        solicitud.setFechaSolicitud(dto.getFechaSolicitud());
        solicitud.setEstado(dto.getEstado());
        solicitud.setReceptor(receptor);

        Solicitud actualizada = solicitudRepository.save(solicitud);
        return mapToDTO(actualizada);
    }

    @Override
    @Transactional
    public void eliminarSolicitud(UUID id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Solicitud no encontrada con ID: " + id));
        solicitudRepository.delete(solicitud);
    }

    // Método auxiliar para convertir una entidad en DTO
    private SolicitudDTO mapToDTO(Solicitud solicitud) {
        SolicitudDTO dto = new SolicitudDTO();
        dto.setIdSolicitud(solicitud.getIdSolicitud());
        dto.setTipoDonacion(solicitud.getTipoDonacion());
        dto.setDescripcion(solicitud.getDescripcion());
        dto.setFechaSolicitud(solicitud.getFechaSolicitud());
        dto.setEstado(solicitud.getEstado());
        dto.setIdReceptor(solicitud.getReceptor().getIdReceptor());
        return dto;
    }
}
