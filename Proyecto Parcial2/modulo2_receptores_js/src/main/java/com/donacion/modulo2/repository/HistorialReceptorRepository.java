package com.donacion.modulo2.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.donacion.modulo2.entity.HistorialReceptor;

@Repository
public interface HistorialReceptorRepository extends JpaRepository<HistorialReceptor, UUID> {
    // Puedes agregar métodos personalizados si los necesitas más adelante
}
