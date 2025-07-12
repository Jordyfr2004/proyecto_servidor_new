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

import com.donacion.modulo2.dto.EntregaDTO;
import com.donacion.modulo2.service.EntregaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * EntregaController:
 * Controlador REST para gestionar las entregas de víveres.
 */
@RestController
@RequestMapping("/api/entregas")
@CrossOrigin(origins = "*")
@Tag(name = "Entrega", description = "Operaciones relacionadas con la entrega de víveres")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    @PostMapping
    @Operation(summary = "Crear entrega", description = "Crea una nueva entrega de víveres para una solicitud existente.")
    public ResponseEntity<EntregaDTO> crearEntrega(@Valid @RequestBody EntregaDTO entregaDTO) {
        EntregaDTO creada = entregaService.crearEntrega(entregaDTO);
        return ResponseEntity.ok(creada);
    }

    @GetMapping
    @Operation(summary = "Listar entregas", description = "Retorna todas las entregas registradas.")
    public ResponseEntity<List<EntregaDTO>> obtenerTodas() {
        return ResponseEntity.ok(entregaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener entrega por ID", description = "Retorna una entrega específica según su ID.")
    public ResponseEntity<EntregaDTO> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(entregaService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar entrega", description = "Actualiza los datos de una entrega existente.")
    public ResponseEntity<EntregaDTO> actualizarEntrega(@PathVariable UUID id, @Valid @RequestBody EntregaDTO entregaDTO) {
        EntregaDTO actualizada = entregaService.actualizarEntrega(id, entregaDTO);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar entrega", description = "Elimina una entrega por su ID.")
    public ResponseEntity<Void> eliminarEntrega(@PathVariable UUID id) {
        entregaService.eliminarEntrega(id);
        return ResponseEntity.noContent().build();
    }
}
