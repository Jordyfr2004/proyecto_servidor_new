package com.donacion.modulo2.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.donacion.modulo2.entity.Entrega;

/**
 * EntregaRepository:
 * Repositorio JPA para la entidad Entrega.
 */
@Repository
public interface EntregaRepository extends JpaRepository<Entrega, UUID> {
}
