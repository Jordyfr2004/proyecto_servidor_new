package com.modulo2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modulo2.model.Direccion;
import com.modulo2.repository.DireccionRepository;
import com.modulo2.service.DireccionService;

@Service
public class DireccionServiceImpl implements DireccionService {

@Autowired
private DireccionRepository direccionRepository;

@Override
public List<Direccion> obtenerTodas() {
    return direccionRepository.findAll();
}

@Override
public Direccion obtenerPorId(Long id) {
    return direccionRepository.findById(id).orElse(null);
}

@Override
public Direccion guardar(Direccion direccion) {
    return direccionRepository.save(direccion);
}

@Override
public void eliminar(Long id) {
    direccionRepository.deleteById(id);
}

}