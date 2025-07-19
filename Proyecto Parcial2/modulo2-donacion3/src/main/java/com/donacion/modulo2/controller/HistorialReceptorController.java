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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donacion.modulo2.dto.HistorialReceptorDTO;
import com.donacion.modulo2.service.HistorialReceptorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/**
 * HistorialReceptorController:
 * Controlador REST para gestionar el historial de los receptores.
 */
@RestController
@RequestMapping("/api/historiales")
@CrossOrigin(origins = "*")
@Tag(name = "HistorialReceptor", description = "Operaciones relacionadas con el historial de los receptores")
public class HistorialReceptorController {

    @Autowired
    private HistorialReceptorService historialService;

    @PostMapping
    @Operation(
        summary = "Crear historial",
        description = "Registra un nuevo historial para un receptor existente",
        requestBody = @RequestBody(
            description = "Datos del historial a crear",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = HistorialReceptorDTO.class),
                examples = @ExampleObject(value = "{ \"descripcion\": \"Entrega completa realizada\", \"fecha\": \"2025-07-13\", \"receptorId\": \"xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx\" }")
            )
        )
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Historial creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<HistorialReceptorDTO> crearHistorial(
            @Valid @org.springframework.web.bind.annotation.RequestBody HistorialReceptorDTO dto) {
        HistorialReceptorDTO creado = historialService.crearHistorial(dto);
        return ResponseEntity.ok(creado);
    }

    @GetMapping
    @Operation(summary = "Listar historiales", description = "Devuelve todos los historiales registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de historiales obtenida correctamente")
    })
    public ResponseEntity<List<HistorialReceptorDTO>> obtenerTodos() {
        return ResponseEntity.ok(historialService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener historial por ID", description = "Devuelve un historial específico por su UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Historial encontrado"),
        @ApiResponse(responseCode = "404", description = "Historial no encontrado")
    })
    public ResponseEntity<HistorialReceptorDTO> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(historialService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar historial",
        description = "Actualiza los datos de un historial existente",
        requestBody = @RequestBody(
            description = "Datos actualizados del historial",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = HistorialReceptorDTO.class),
                examples = @ExampleObject(value = "{ \"descripcion\": \"Revisión de entrega realizada\", \"fecha\": \"2025-07-14\", \"receptorId\": \"xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx\" }")
            )
        )
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Historial actualizado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404", description = "Historial no encontrado")
    })
    public ResponseEntity<HistorialReceptorDTO> actualizarHistorial(
            @PathVariable UUID id,
            @Valid @org.springframework.web.bind.annotation.RequestBody HistorialReceptorDTO dto) {
        return ResponseEntity.ok(historialService.actualizarHistorial(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar historial", description = "Elimina un historial por su UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Historial eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Historial no encontrado")
    })
    public ResponseEntity<Void> eliminarHistorial(@PathVariable UUID id) {
        historialService.eliminarHistorial(id);
        return ResponseEntity.noContent().build();
    }
}
