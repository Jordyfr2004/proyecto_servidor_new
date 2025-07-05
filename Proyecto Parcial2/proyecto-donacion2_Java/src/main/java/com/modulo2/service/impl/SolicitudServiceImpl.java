package com.modulo2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modulo2.model.Receptor;
import com.modulo2.model.Solicitud;
import com.modulo2.repository.ReceptorRepository;
import com.modulo2.repository.SolicitudRepository;
import com.modulo2.service.SolicitudService;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private ReceptorRepository receptorRepository;

    @Override
    public List<Solicitud> obtenerTodas() {
        return solicitudRepository.findAll();
    }

    @Override
    public Solicitud obtenerPorId(Long id) {
        return solicitudRepository.findById(id).orElse(null);
    }

    @Override
    public Solicitud guardar(Solicitud solicitud) {
        if (solicitud.getReceptor() != null && solicitud.getReceptor().getId() != null) {
            Receptor receptor = receptorRepository.findById(solicitud.getReceptor().getId())
                    .orElseThrow(() -> new RuntimeException("Receptor no encontrado con ID: " + solicitud.getReceptor().getId()));
            solicitud.setReceptor(receptor);
        }
        return solicitudRepository.save(solicitud);
    }

    @Override
    public void eliminar(Long id) {
        solicitudRepository.deleteById(id);
    }
}
