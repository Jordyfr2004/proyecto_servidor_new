package com.modulo2.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

import com.modulo2.dto.ReceptorDTO;
import com.modulo2.mappers.ReceptorMapper;
import com.modulo2.model.Receptor;
import com.modulo2.service.ReceptorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/receptores")
public class ReceptorController {

    @Autowired
    private ReceptorService receptorService;

    @GetMapping
    public List<ReceptorDTO> obtenerTodos() {
        return receptorService.obtenerTodos().stream()
                .map(ReceptorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceptorDTO> obtenerPorId(@PathVariable Long id) {
        Receptor receptor = receptorService.obtenerPorId(id);
        return receptor != null ? ResponseEntity.ok(ReceptorMapper.toDTO(receptor)) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ReceptorDTO> crear(@Valid @RequestBody ReceptorDTO dto) {
        Receptor nuevo = ReceptorMapper.toEntity(dto);
        Receptor guardado = receptorService.guardar(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(ReceptorMapper.toDTO(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceptorDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ReceptorDTO dto) {
        if (receptorService.obtenerPorId(id) != null) {
            Receptor actualizado = ReceptorMapper.toEntity(dto);
            actualizado.setId(id);
            return ResponseEntity.ok(ReceptorMapper.toDTO(receptorService.guardar(actualizado)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (receptorService.obtenerPorId(id) != null) {
            receptorService.eliminar(id);
            return ResponseEntity.ok().body(Collections.singletonMap("mensaje", "El receptor ha sido eliminado correctamente."));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", "Receptor no encontrado con ID: " + id));
        }
    }
}
