package com.modulo2.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modulo2.dto.HistorialReceptorDTO;
import com.modulo2.mappers.HistorialReceptorMapper;
import com.modulo2.model.HistorialReceptor;
import com.modulo2.model.Receptor;
import com.modulo2.service.HistorialReceptorService;
import com.modulo2.service.ReceptorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/historiales")
@Validated
public class HistorialReceptorController {

@Autowired
private HistorialReceptorService historialService;

@Autowired
private ReceptorService receptorService;

@GetMapping
public List<HistorialReceptorDTO> listar() {
    return historialService.obtenerTodos().stream()
            .map(HistorialReceptorMapper::toDTO)
            .collect(Collectors.toList());
}

@GetMapping("/{id}")
public ResponseEntity<HistorialReceptorDTO> obtenerPorId(@PathVariable Long id) {
    HistorialReceptor historial = historialService.obtenerPorId(id);
    if (historial != null) {
        return ResponseEntity.ok(HistorialReceptorMapper.toDTO(historial));
    } else {
        return ResponseEntity.notFound().build();
    }
}

@PostMapping
public ResponseEntity<HistorialReceptorDTO> crear(@Valid @RequestBody HistorialReceptorDTO dto) {
    Receptor receptor = receptorService.obtenerPorId(dto.getReceptorId());
    if (receptor == null) {
        return ResponseEntity.badRequest().body(null);
    }

    HistorialReceptor historial = HistorialReceptorMapper.toEntity(dto, receptor);
    HistorialReceptor guardado = historialService.guardar(historial);
    return ResponseEntity.ok(HistorialReceptorMapper.toDTO(guardado));
}

@PutMapping("/{id}")
public ResponseEntity<HistorialReceptorDTO> actualizar(@PathVariable Long id, @Valid @RequestBody HistorialReceptorDTO dto) {
    HistorialReceptor existente = historialService.obtenerPorId(id);
    if (existente == null) {
        return ResponseEntity.notFound().build();
    }

    Receptor receptor = receptorService.obtenerPorId(dto.getReceptorId());
    if (receptor == null) {
        return ResponseEntity.badRequest().body(null);
    }

    HistorialReceptor actualizado = HistorialReceptorMapper.toEntity(dto, receptor);
    actualizado.setId(id);
    HistorialReceptor guardado = historialService.guardar(actualizado);
    return ResponseEntity.ok(HistorialReceptorMapper.toDTO(guardado));
}

@DeleteMapping("/{id}")
public ResponseEntity<?> eliminar(@PathVariable Long id) {
    if (historialService.obtenerPorId(id) != null) {
        historialService.eliminar(id);
        return ResponseEntity.ok().body("{\"mensaje\": \"Historial eliminado correctamente.\"}");
    } else {
        return ResponseEntity.notFound().build();
    }
}

// Ruta adicional para evaluar prioridad con "IA simulada"
@GetMapping("/evaluar-prioridad/{receptorId}")
public ResponseEntity<String> evaluarPrioridad(@PathVariable Long receptorId) {
    String prioridad = historialService.evaluarPrioridad(receptorId);
    return ResponseEntity.ok("{\"prioridad\": \"" + prioridad + "\"}");
}

}