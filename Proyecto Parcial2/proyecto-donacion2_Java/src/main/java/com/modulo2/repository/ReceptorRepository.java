package com.modulo2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modulo2.model.Receptor;

public interface ReceptorRepository extends JpaRepository<Receptor, Long> {
    
}