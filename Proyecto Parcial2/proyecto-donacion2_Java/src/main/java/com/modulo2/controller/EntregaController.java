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

import com.modulo2.dto.EntregaDTO;
import com.modulo2.mappers.EntregaMapper;
import com.modulo2.model.Donacion;
import com.modulo2.model.Entrega;
import com.modulo2.model.Solicitud;
import com.modulo2.service.DonacionService;
import com.modulo2.service.EntregaService;
import com.modulo2.service.SolicitudService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/entregas")
public class EntregaController {

@Autowired
private EntregaService entregaService;

@Autowired
private SolicitudService solicitudService;

@Autowired
private DonacionService donacionService;

@GetMapping
public List<EntregaDTO> listar() {
    return entregaService.obtenerTodas().stream()
            .map(EntregaMapper::toDTO)
            .collect(Collectors.toList());
}

@GetMapping("/{id}")
public ResponseEntity<EntregaDTO> obtenerPorId(@PathVariable Long id) {
    Entrega entrega = entregaService.obtenerPorId(id);
    if (entrega != null) {
        return ResponseEntity.ok(EntregaMapper.toDTO(entrega));
    } else {
        return ResponseEntity.notFound().build();
    }
}

@PostMapping
public ResponseEntity<EntregaDTO> crear(@Valid @RequestBody EntregaDTO dto) {
    if (dto.getSolicitudId() == null || dto.getDonacionId() == null) {
        return ResponseEntity.badRequest().build();
    }

    Solicitud solicitud = solicitudService.obtenerPorId(dto.getSolicitudId());
    Donacion donacion = donacionService.obtenerPorId(dto.getDonacionId());

    if (solicitud == null || donacion == null) {
        return ResponseEntity.badRequest().build();
    }

    Entrega nueva = EntregaMapper.toEntity(dto, solicitud, donacion);
    Entrega guardada = entregaService.guardar(nueva);
    return ResponseEntity.ok(EntregaMapper.toDTO(guardada));
}

@PutMapping("/{id}")
public ResponseEntity<EntregaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody EntregaDTO dto) {
    Entrega existente = entregaService.obtenerPorId(id);
    if (existente == null) {
        return ResponseEntity.notFound().build();
    }

    if (dto.getSolicitudId() == null || dto.getDonacionId() == null) {
        return ResponseEntity.badRequest().build();
    }

    Solicitud solicitud = solicitudService.obtenerPorId(dto.getSolicitudId());
    Donacion donacion = donacionService.obtenerPorId(dto.getDonacionId());

    if (solicitud == null || donacion == null) {
        return ResponseEntity.badRequest().build();
    }

    Entrega actualizada = EntregaMapper.toEntity(dto, solicitud, donacion);
    actualizada.setId(id);
    Entrega guardada = entregaService.guardar(actualizada);
    return ResponseEntity.ok(EntregaMapper.toDTO(guardada));
}

@DeleteMapping("/{id}")
public ResponseEntity<?> eliminar(@PathVariable Long id) {
    if (entregaService.obtenerPorId(id) != null) {
        entregaService.eliminar(id);
        return ResponseEntity.ok().body("{\"mensaje\": \"Entrega eliminada correctamente.\"}");
    } else {
        return ResponseEntity.notFound().build();
    }
}

}