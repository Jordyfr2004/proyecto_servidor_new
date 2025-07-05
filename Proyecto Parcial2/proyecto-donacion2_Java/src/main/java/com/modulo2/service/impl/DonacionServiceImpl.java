package com.modulo2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modulo2.model.Donacion;
import com.modulo2.repository.DonacionRepository;
import com.modulo2.service.DonacionService;

@Service
public class DonacionServiceImpl implements DonacionService {

@Autowired
private DonacionRepository donacionRepository;

@Override
public Donacion obtenerPorId(Long id) {
    return donacionRepository.findById(id).orElse(null);
}

@Override
public Donacion guardar(Donacion donacion) {
    return donacionRepository.save(donacion);
}
}