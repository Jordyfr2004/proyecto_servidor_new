package com.donacion.modulo2.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donacion.modulo2.dto.DireccionDTO;
import com.donacion.modulo2.entity.Direccion;
import com.donacion.modulo2.entity.Receptor;
import com.donacion.modulo2.exception.RecursoNoEncontradoException;
import com.donacion.modulo2.repository.DireccionRepository;
import com.donacion.modulo2.repository.ReceptorRepository;
import com.donacion.modulo2.service.DireccionService;

import jakarta.transaction.Transactional;

@Service
public class DireccionServiceImpl implements DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private ReceptorRepository receptorRepository;

    @Override
    @Transactional
    public DireccionDTO crearDireccion(DireccionDTO dto) {
        Receptor receptor = receptorRepository.findById(dto.getIdReceptor())
                .orElseThrow(() -> new RecursoNoEncontradoException("Receptor no encontrado con ID: " + dto.getIdReceptor()));

        Direccion direccion = new Direccion();
        direccion.setProvincia(dto.getProvincia());
        direccion.setCiudad(dto.getCiudad());
        direccion.setCalle(dto.getCalle()); // CORREGIDO
        direccion.setReferencia(dto.getReferencia());
        direccion.setReceptor(receptor);

        Direccion guardada = direccionRepository.save(direccion);
        return mapToDTO(guardada);
    }

    @Override
    public List<DireccionDTO> obtenerTodas() {
        return direccionRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DireccionDTO obtenerPorId(UUID id) {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Dirección no encontrada con ID: " + id));
        return mapToDTO(direccion);
    }

    @Override
    @Transactional
    public DireccionDTO actualizarDireccion(UUID id, DireccionDTO dto) {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Dirección no encontrada con ID: " + id));

        Receptor receptor = receptorRepository.findById(dto.getIdReceptor())
                .orElseThrow(() -> new RecursoNoEncontradoException("Receptor no encontrado con ID: " + dto.getIdReceptor()));

        direccion.setProvincia(dto.getProvincia());
        direccion.setCiudad(dto.getCiudad());
        direccion.setCalle(dto.getCalle()); // CORREGIDO
        direccion.setReferencia(dto.getReferencia());
        direccion.setReceptor(receptor);

        Direccion actualizada = direccionRepository.save(direccion);
        return mapToDTO(actualizada);
    }

    @Override
    @Transactional
    public void eliminarDireccion(UUID id) {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Dirección no encontrada con ID: " + id));
        direccionRepository.delete(direccion);
    }

    // Método auxiliar
    private DireccionDTO mapToDTO(Direccion direccion) {
        DireccionDTO dto = new DireccionDTO();
        dto.setIdDireccion(direccion.getIdDireccion());
        dto.setProvincia(direccion.getProvincia());
        dto.setCiudad(direccion.getCiudad());
        dto.setCalle(direccion.getCalle()); // CORREGIDO
        dto.setReferencia(direccion.getReferencia());
        dto.setIdReceptor(direccion.getReceptor().getIdReceptor());
        return dto;
    }
}
