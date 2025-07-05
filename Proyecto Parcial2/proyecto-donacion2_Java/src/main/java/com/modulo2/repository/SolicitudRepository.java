package com.modulo2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modulo2.model.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
}
