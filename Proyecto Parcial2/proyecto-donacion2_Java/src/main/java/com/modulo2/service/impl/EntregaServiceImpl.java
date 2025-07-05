package com.modulo2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modulo2.model.Entrega;
import com.modulo2.repository.EntregaRepository;
import com.modulo2.service.EntregaService;

@Service
public class EntregaServiceImpl implements EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Override
    public List<Entrega> obtenerTodas() {
        return entregaRepository.findAll();
    }

    @Override
    public Entrega obtenerPorId(Long id) {
        return entregaRepository.findById(id).orElse(null);
    }

    @Override
    public Entrega guardar(Entrega entrega) {
        return entregaRepository.save(entrega);
    }

    @Override
    public void eliminar(Long id) {
        entregaRepository.deleteById(id);
    }
}
