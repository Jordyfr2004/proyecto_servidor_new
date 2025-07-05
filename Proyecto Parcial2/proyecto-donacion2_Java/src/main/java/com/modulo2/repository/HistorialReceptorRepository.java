package com.modulo2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modulo2.model.HistorialReceptor;

public interface HistorialReceptorRepository extends JpaRepository<HistorialReceptor, Long> {
    List<HistorialReceptor> findByReceptorId(Long receptorId);
}
