package com.donacion.modulo2.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.donacion.modulo2.entity.Receptor;

/**
 * ReceptorRepository:
 * Interfaz que extiende JpaRepository para realizar operaciones CRUD
 * sobre la entidad Receptor.
 */
@Repository
public interface ReceptorRepository extends JpaRepository<Receptor, UUID> {

    /**
     * Busca un receptor por su correo electrónico.
     *
     * @param correo el correo del receptor.
     * @return Optional con el receptor si existe.
     */
    Optional<Receptor> findByCorreo(String correo);

    /**
     * Verifica si ya existe un receptor con el correo especificado.
     *
     * @param correo el correo a verificar.
     * @return true si existe, false si no.
     */
    boolean existsByCorreo(String correo);

}
