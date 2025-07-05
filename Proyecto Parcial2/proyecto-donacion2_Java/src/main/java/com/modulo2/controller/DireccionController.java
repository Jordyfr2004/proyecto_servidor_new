package com.modulo2.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modulo2.dto.DireccionDTO;
import com.modulo2.mappers.DireccionMapper;
import com.modulo2.model.Direccion;
import com.modulo2.model.Receptor;
import com.modulo2.service.DireccionService;
import com.modulo2.service.ReceptorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/direcciones")
public class DireccionController {

@Autowired
private DireccionService direccionService;

@Autowired
private ReceptorService receptorService;

@GetMapping
public List<DireccionDTO> listar() {
    return direccionService.obtenerTodas().stream()
            .map(DireccionMapper::toDTO)
            .collect(Collectors.toList());
}

@GetMapping("/{id}")
public ResponseEntity<DireccionDTO> obtenerPorId(@PathVariable Long id) {
    Direccion direccion = direccionService.obtenerPorId(id);
    if (direccion != null) {
        return ResponseEntity.ok(DireccionMapper.toDTO(direccion));
    } else {
        return ResponseEntity.notFound().build();
    }
}

@PostMapping
public ResponseEntity<DireccionDTO> crear(@Valid @RequestBody DireccionDTO dto) {
    if (dto.getReceptorId() == null) {
        return ResponseEntity.badRequest().build();
    }

    Receptor receptor = receptorService.obtenerPorId(dto.getReceptorId());
    if (receptor == null) {
        return ResponseEntity.badRequest().build();
    }

    Direccion direccion = DireccionMapper.toEntity(dto, receptor);
    Direccion guardada = direccionService.guardar(direccion);
    return ResponseEntity.ok(DireccionMapper.toDTO(guardada));
}

@PutMapping("/{id}")
public ResponseEntity<DireccionDTO> actualizar(@PathVariable Long id, @Valid @RequestBody DireccionDTO dto) {
    Direccion existente = direccionService.obtenerPorId(id);
    if (existente == null) {
        return ResponseEntity.notFound().build();
    }

    if (dto.getReceptorId() == null) {
        return ResponseEntity.badRequest().build();
    }

    Receptor receptor = receptorService.obtenerPorId(dto.getReceptorId());
    if (receptor == null) {
        return ResponseEntity.badRequest().build();
    }

    Direccion actualizada = DireccionMapper.toEntity(dto, receptor);
    actualizada.setId(id);
    Direccion guardada = direccionService.guardar(actualizada);
    return ResponseEntity.ok(DireccionMapper.toDTO(guardada));
}

@DeleteMapping("/{id}")
public ResponseEntity<?> eliminar(@PathVariable Long id) {
    if (direccionService.obtenerPorId(id) != null) {
        direccionService.eliminar(id);
        return ResponseEntity.ok().body("{\"mensaje\": \"Dirección eliminada correctamente.\"}");
    } else {
        return ResponseEntity.notFound().build();
    }
}

}