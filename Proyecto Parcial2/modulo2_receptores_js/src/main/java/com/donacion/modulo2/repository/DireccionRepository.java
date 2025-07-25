package com.donacion.modulo2.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.donacion.modulo2.entity.Direccion;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, UUID> {
    // Puedes agregar métodos personalizados si es necesario
}
