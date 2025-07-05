package com.modulo2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modulo2.model.Direccion;

public interface DireccionRepository extends JpaRepository<Direccion, Long> {
}