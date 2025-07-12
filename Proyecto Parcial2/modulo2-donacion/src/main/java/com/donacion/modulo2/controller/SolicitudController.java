package com.donacion.modulo2.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donacion.modulo2.dto.SolicitudDTO;
import com.donacion.modulo2.service.SolicitudService;

import jakarta.validation.Valid;

/**
 * Controlador REST para gestionar las solicitudes.
 */
@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    /**
     * Crea una nueva solicitud.
     */
    @PostMapping
    public ResponseEntity<SolicitudDTO> crearSolicitud(@Valid @RequestBody SolicitudDTO solicitudDTO) {
        SolicitudDTO creada = solicitudService.crearSolicitud(solicitudDTO);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    /**
     * Obtiene todas las solicitudes.
     */
    @GetMapping
    public ResponseEntity<List<SolicitudDTO>> obtenerTodas() {
        return ResponseEntity.ok(solicitudService.obtenerTodas());
    }

    /**
     * Obtiene una solicitud por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SolicitudDTO> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(solicitudService.obtenerPorId(id));
    }

    /**
     * Actualiza una solicitud existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SolicitudDTO> actualizar(@PathVariable UUID id, @Valid @RequestBody SolicitudDTO dto) {
        return ResponseEntity.ok(solicitudService.actualizarSolicitud(id, dto));
    }

    /**
     * Elimina una solicitud.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        solicitudService.eliminarSolicitud(id);
        return ResponseEntity.noContent().build();
    }
}
