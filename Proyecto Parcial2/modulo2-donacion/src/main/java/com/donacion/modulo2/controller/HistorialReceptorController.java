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

import com.donacion.modulo2.dto.HistorialReceptorDTO;
import com.donacion.modulo2.service.HistorialReceptorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/historiales")
@CrossOrigin(origins = "*")
@Tag(name = "HistorialReceptor", description = "Operaciones relacionadas con el historial de los receptores")
public class HistorialReceptorController {

    @Autowired
    private HistorialReceptorService historialService;

    @PostMapping
    @Operation(summary = "Crear historial", description = "Registra un nuevo historial para un receptor existente")
    public ResponseEntity<HistorialReceptorDTO> crearHistorial(@Valid @RequestBody HistorialReceptorDTO dto) {
        HistorialReceptorDTO creado = historialService.crearHistorial(dto);
        return ResponseEntity.ok(creado);
    }

    @GetMapping
    @Operation(summary = "Listar historiales", description = "Devuelve todos los historiales registrados")
    public ResponseEntity<List<HistorialReceptorDTO>> obtenerTodos() {
        return ResponseEntity.ok(historialService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener historial por ID", description = "Devuelve un historial específico por su ID")
    public ResponseEntity<HistorialReceptorDTO> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(historialService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar historial", description = "Actualiza los datos de un historial existente")
    public ResponseEntity<HistorialReceptorDTO> actualizarHistorial(@PathVariable UUID id, @Valid @RequestBody HistorialReceptorDTO dto) {
        return ResponseEntity.ok(historialService.actualizarHistorial(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar historial", description = "Elimina un historial por su ID")
    public ResponseEntity<Void> eliminarHistorial(@PathVariable UUID id) {
        historialService.eliminarHistorial(id);
        return ResponseEntity.noContent().build();
    }
}
