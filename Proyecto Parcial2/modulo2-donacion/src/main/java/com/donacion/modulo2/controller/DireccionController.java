package com.donacion.modulo2.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donacion.modulo2.dto.DireccionDTO;
import com.donacion.modulo2.service.DireccionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * DireccionController:
 * Controlador REST para gestionar direcciones de los receptores.
 */
@RestController
@RequestMapping("/api/direcciones")
@CrossOrigin(origins = "*")
@Tag(name = "Dirección", description = "Operaciones relacionadas con las direcciones de los receptores")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    @PostMapping
    @Operation(summary = "Crear dirección", description = "Crea una nueva dirección asociada a un receptor.")
    public ResponseEntity<DireccionDTO> crearDireccion(@Valid @RequestBody DireccionDTO direccionDTO) {
        DireccionDTO creada = direccionService.crearDireccion(direccionDTO);
        return ResponseEntity.ok(creada);
    }

    @GetMapping
    @Operation(summary = "Listar direcciones", description = "Retorna todas las direcciones registradas.")
    public ResponseEntity<List<DireccionDTO>> obtenerTodas() {
        return ResponseEntity.ok(direccionService.obtenerTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener dirección por ID", description = "Retorna una dirección específica según su ID.")
    public ResponseEntity<DireccionDTO> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(direccionService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar dirección", description = "Actualiza los datos de una dirección existente.")
    public ResponseEntity<DireccionDTO> actualizarDireccion(@PathVariable UUID id, @Valid @RequestBody DireccionDTO direccionDTO) {
        DireccionDTO actualizada = direccionService.actualizarDireccion(id, direccionDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar dirección", description = "Elimina una dirección por su ID.")
    public ResponseEntity<Void> eliminarDireccion(@PathVariable UUID id) {
        direccionService.eliminarDireccion(id);
        return ResponseEntity.noContent().build();
    }
}
