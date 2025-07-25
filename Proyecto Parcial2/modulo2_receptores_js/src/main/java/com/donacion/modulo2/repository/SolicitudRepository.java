package com.donacion.modulo2.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.donacion.modulo2.entity.Solicitud;

/**
 * Repositorio para la entidad Solicitud.
 * Permite operaciones CRUD utilizando Spring Data JPA.
 */
@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, UUID> {
    // Puedes agregar consultas personalizadas si lo necesitas
}
