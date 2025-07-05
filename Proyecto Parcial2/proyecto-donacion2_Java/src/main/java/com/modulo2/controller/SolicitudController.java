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

import com.modulo2.dto.SolicitudDTO;
import com.modulo2.mappers.SolicitudMapper;
import com.modulo2.model.Receptor;
import com.modulo2.model.Solicitud;
import com.modulo2.service.ReceptorService;
import com.modulo2.service.SolicitudService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

@Autowired
private SolicitudService solicitudService;

@Autowired
private ReceptorService receptorService;

@GetMapping
public List<SolicitudDTO> listar() {
    return solicitudService.obtenerTodas().stream()
            .map(SolicitudMapper::toDTO)
            .collect(Collectors.toList());
}

@GetMapping("/{id}")
public ResponseEntity<SolicitudDTO> obtenerPorId(@PathVariable Long id) {
    Solicitud solicitud = solicitudService.obtenerPorId(id);
    if (solicitud != null) {
        return ResponseEntity.ok(SolicitudMapper.toDTO(solicitud));
    } else {
        return ResponseEntity.notFound().build();
    }
}

@PostMapping
public ResponseEntity<SolicitudDTO> crear(@Valid @RequestBody SolicitudDTO dto) {
    if (dto.getReceptor() == null || dto.getReceptor().getId() == null) {
        return ResponseEntity.badRequest().build();
    }

    Receptor receptor = receptorService.obtenerPorId(dto.getReceptor().getId());
    if (receptor == null) {
        return ResponseEntity.badRequest().build();
    }

    Solicitud entidad = SolicitudMapper.toEntity(dto);
    entidad.setReceptor(receptor);

    Solicitud guardada = solicitudService.guardar(entidad);
    return ResponseEntity.ok(SolicitudMapper.toDTO(guardada));
}

@PutMapping("/{id}")
public ResponseEntity<SolicitudDTO> actualizar(@PathVariable Long id, @Valid @RequestBody SolicitudDTO dto) {
    Solicitud existente = solicitudService.obtenerPorId(id);
    if (existente == null) {
        return ResponseEntity.notFound().build();
    }

    if (dto.getReceptor() == null || dto.getReceptor().getId() == null) {
        return ResponseEntity.badRequest().build();
    }

    Receptor receptor = receptorService.obtenerPorId(dto.getReceptor().getId());
    if (receptor == null) {
        return ResponseEntity.badRequest().build();
    }

    Solicitud actualizada = SolicitudMapper.toEntity(dto);
    actualizada.setId(id);
    actualizada.setReceptor(receptor);

    Solicitud guardada = solicitudService.guardar(actualizada);
    return ResponseEntity.ok(SolicitudMapper.toDTO(guardada));
}

@DeleteMapping("/{id}")
public ResponseEntity<?> eliminar(@PathVariable Long id) {
    Solicitud existente = solicitudService.obtenerPorId(id);
    if (existente != null) {
        solicitudService.eliminar(id);
        return ResponseEntity.ok().body("{\"mensaje\": \"Solicitud eliminada correctamente.\"}");
    } else {
        return ResponseEntity.notFound().build();
    }
}

}