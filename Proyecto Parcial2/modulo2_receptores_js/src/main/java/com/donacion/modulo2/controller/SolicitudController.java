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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donacion.modulo2.dto.SolicitudDTO;
import com.donacion.modulo2.service.SolicitudService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

/**
 * Controlador REST para gestionar las solicitudes.
 */
@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @Operation(
        summary = "Crear una nueva solicitud",
        requestBody = @RequestBody(
            description = "Datos de la nueva solicitud",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = SolicitudDTO.class),
                examples = @ExampleObject(value = "{ \"fechaSolicitud\": \"2025-07-13\", \"estado\": \"PENDIENTE\", \"receptorId\": \"e3f734d2-1122-445b-889f-3e33d50d2a0d\" }")
            )
        )
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Solicitud creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<SolicitudDTO> crearSolicitud(@Valid @org.springframework.web.bind.annotation.RequestBody SolicitudDTO solicitudDTO) {
        SolicitudDTO creada = solicitudService.crearSolicitud(solicitudDTO);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener todas las solicitudes")
    @ApiResponse(responseCode = "200", description = "Lista de solicitudes obtenida correctamente")
    @GetMapping
    public ResponseEntity<List<SolicitudDTO>> obtenerTodas() {
        return ResponseEntity.ok(solicitudService.obtenerTodas());
    }

    @Operation(summary = "Obtener una solicitud por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Solicitud encontrada"),
        @ApiResponse(responseCode = "404", description = "Solicitud no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SolicitudDTO> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(solicitudService.obtenerPorId(id));
    }

    @Operation(
        summary = "Actualizar una solicitud existente",
        requestBody = @RequestBody(
            description = "Datos actualizados de la solicitud",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = SolicitudDTO.class),
                examples = @ExampleObject(value = "{ \"fechaSolicitud\": \"2025-07-13\", \"estado\": \"APROBADA\", \"receptorId\": \"e3f734d2-1122-445b-889f-3e33d50d2a0d\" }")
            )
        )
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Solicitud actualizada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404", description = "Solicitud no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SolicitudDTO> actualizar(
            @PathVariable UUID id,
            @Valid @org.springframework.web.bind.annotation.RequestBody SolicitudDTO dto) {
        return ResponseEntity.ok(solicitudService.actualizarSolicitud(id, dto));
    }

    @Operation(summary = "Eliminar una solicitud por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Solicitud eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Solicitud no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
        solicitudService.eliminarSolicitud(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar solicitudes por estado")
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<SolicitudDTO>> buscarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(solicitudService.obtenerPorEstado(estado));
    }

    @Operation(summary = "Buscar solicitudes por receptor")
    @GetMapping("/receptor/{receptorId}")
    public ResponseEntity<List<SolicitudDTO>> buscarPorReceptor(@PathVariable UUID receptorId) {
        return ResponseEntity.ok(solicitudService.obtenerPorReceptor(receptorId));
    }

    @Operation(summary = "Buscar solicitudes por tipo de donación")
    @GetMapping("/tipo/{tipoDonacion}")
    public ResponseEntity<List<SolicitudDTO>> buscarPorTipoDonacion(@PathVariable String tipoDonacion) {
        return ResponseEntity.ok(solicitudService.obtenerPorTipoDonacion(tipoDonacion));
    }

    @Operation(summary = "Cambiar estado de una solicitud")
    @PutMapping("/{id}/estado/{nuevoEstado}")
    public ResponseEntity<SolicitudDTO> cambiarEstado(
            @PathVariable UUID id, 
            @PathVariable String nuevoEstado) {
        return ResponseEntity.ok(solicitudService.cambiarEstado(id, nuevoEstado));
    }

    @Operation(summary = "Contar solicitudes por estado")
    @GetMapping("/contar/estado/{estado}")
    public ResponseEntity<Long> contarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(solicitudService.contarPorEstado(estado));
    }

    @Operation(summary = "Buscar solicitudes por receptor y estado")
    @GetMapping("/receptor/{receptorId}/estado/{estado}")
    public ResponseEntity<List<SolicitudDTO>> buscarPorReceptorYEstado(
            @PathVariable UUID receptorId, 
            @PathVariable String estado) {
        return ResponseEntity.ok(solicitudService.obtenerPorReceptorYEstado(receptorId, estado));
    }
}
