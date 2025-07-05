package com.modulo2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modulo2.model.Receptor;
import com.modulo2.repository.ReceptorRepository;

@Service
public class ReceptorServiceImpl implements ReceptorService {

@Autowired
private ReceptorRepository receptorRepository;

@Override
public List<Receptor> obtenerTodos() {
    return receptorRepository.findAll();
}

@Override
public Receptor obtenerPorId(Long id) {
    return receptorRepository.findById(id).orElse(null);
}

@Override
public Receptor guardar(Receptor receptor) {
    return receptorRepository.save(receptor);
}

@Override
public void eliminar(Long id) {
    receptorRepository.deleteById(id);
}
}