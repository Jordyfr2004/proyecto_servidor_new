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

import com.donacion.modulo2.dto.EntregaDTO;
import com.donacion.modulo2.service.EntregaService;

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

    /**
     * Endpoint para crear una nueva entrega.
     * Incluye documentación Swagger con ejemplo de entrada.
     */
    @PostMapping
    @Operation(
        summary = "Crear entrega",
        description = "Crea una nueva entrega de víveres para una solicitud existente.",
        requestBody = @RequestBody(
            description = "Datos de la entrega",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = EntregaDTO.class),
                examples = @ExampleObject(value = "{ \"fechaEntrega\": \"2025-07-13\", \"estadoEntrega\": \"REALIZADA\", \"solicitudId\": \"d7a0d1b6-4ed9-49ff-92fb-69e1b0cbf2a3\" }")
            )
        )
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Entrega creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<EntregaDTO> crearEntrega(@Valid @org.springframework.web.bind.annotation.RequestBody EntregaDTO entregaDTO) {
        EntregaDTO creada = entregaService.crearEntrega(entregaDTO);
        return ResponseEntity.ok(creada);
    }

    /**
     * Endpoint para listar todas las entregas registradas.
     */
    @GetMapping
    @Operation(summary = "Listar entregas", description = "Retorna todas las entregas registradas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de entregas obtenida correctamente")
    })
    public ResponseEntity<List<EntregaDTO>> obtenerTodas() {
        return ResponseEntity.ok(entregaService.obtenerTodas());
    }

    /**
     * Endpoint para obtener una entrega específica por su ID.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener entrega por ID", description = "Retorna una entrega específica según su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Entrega encontrada"),
        @ApiResponse(responseCode = "404", description = "Entrega no encontrada")
    })
    public ResponseEntity<EntregaDTO> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(entregaService.obtenerPorId(id));
    }

    /**
     * Endpoint para actualizar los datos de una entrega.
     */
    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar entrega",
        description = "Actualiza los datos de una entrega existente.",
        requestBody = @RequestBody(
            description = "Datos actualizados de la entrega",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = EntregaDTO.class),
                examples = @ExampleObject(value = "{ \"fechaEntrega\": \"2025-07-14\", \"estadoEntrega\": \"EN PROCESO\", \"solicitudId\": \"d7a0d1b6-4ed9-49ff-92fb-69e1b0cbf2a3\" }")
            )
        )
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Entrega actualizada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404", description = "Entrega no encontrada")
    })
    public ResponseEntity<EntregaDTO> actualizarEntrega(
            @PathVariable UUID id,
            @Valid @org.springframework.web.bind.annotation.RequestBody EntregaDTO entregaDTO) {
        EntregaDTO actualizada = entregaService.actualizarEntrega(id, entregaDTO);
        return ResponseEntity.ok(actualizada);
    }

    /**
     * Endpoint para eliminar una entrega por su ID.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar entrega", description = "Elimina una entrega por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Entrega eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Entrega no encontrada")
    })
    public ResponseEntity<Void> eliminarEntrega(@PathVariable UUID id) {
        entregaService.eliminarEntrega(id);
        return ResponseEntity.noContent().build();
    }
}
