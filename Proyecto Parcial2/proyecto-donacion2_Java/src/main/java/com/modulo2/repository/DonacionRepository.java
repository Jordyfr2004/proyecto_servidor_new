package com.modulo2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modulo2.model.Donacion;

public interface DonacionRepository extends JpaRepository<Donacion, Long> {}