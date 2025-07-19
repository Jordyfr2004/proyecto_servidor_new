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

import com.donacion.modulo2.dto.DireccionDTO;
import com.donacion.modulo2.service.DireccionService;

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
 * DireccionController:
 * Controlador REST para gestionar direcciones de los receptores.
 */
@RestController
@RequestMapping("/api/direcciones")
@CrossOrigin(origins = "*")
@Tag(name = "Dirección", description = "Operaciones relacionadas con las direcciones de los receptores")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    /**
     * Crea una nueva dirección.
     */
    @PostMapping
    @Operation(
        summary = "Crear dirección",
        description = "Crea una nueva dirección asociada a un receptor.",
        requestBody = @RequestBody(
            description = "Datos de la nueva dirección",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DireccionDTO.class),
                examples = @ExampleObject(value = "{ \"ciudad\": \"Manta\", \"provincia\": \"Manabí\", \"callePrincipal\": \"Av. 24\", \"calleSecundaria\": \"Calle J\", \"referencia\": \"Frente al parque\", \"receptorId\": \"xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx\" }")
            )
        )
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Dirección creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<DireccionDTO> crearDireccion(@Valid @org.springframework.web.bind.annotation.RequestBody DireccionDTO direccionDTO) {
        DireccionDTO creada = direccionService.crearDireccion(direccionDTO);
        return ResponseEntity.ok(creada);
    }

    /**
     * Lista todas las direcciones.
     */
    @GetMapping
    @Operation(summary = "Listar direcciones", description = "Retorna todas las direcciones registradas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de direcciones obtenida correctamente")
    })
    public ResponseEntity<List<DireccionDTO>> obtenerTodas() {
        return ResponseEntity.ok(direccionService.obtenerTodas());
    }

    /**
     * Obtiene una dirección por su UUID.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener dirección por ID", description = "Retorna una dirección específica según su UUID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Dirección encontrada"),
        @ApiResponse(responseCode = "404", description = "Dirección no encontrada")
    })
    public ResponseEntity<DireccionDTO> obtenerPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(direccionService.obtenerPorId(id));
    }

    /**
     * Actualiza una dirección por su UUID.
     */
    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar dirección",
        description = "Actualiza los datos de una dirección existente.",
        requestBody = @RequestBody(
            description = "Datos actualizados de la dirección",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = DireccionDTO.class),
                examples = @ExampleObject(value = "{ \"ciudad\": \"Portoviejo\", \"provincia\": \"Manabí\", \"callePrincipal\": \"Av. América\", \"calleSecundaria\": \"Calle 15\", \"referencia\": \"Cerca del colegio\", \"receptorId\": \"xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx\" }")
            )
        )
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Dirección actualizada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404", description = "Dirección no encontrada")
    })
    public ResponseEntity<DireccionDTO> actualizarDireccion(
            @PathVariable UUID id,
            @Valid @org.springframework.web.bind.annotation.RequestBody DireccionDTO direccionDTO) {
        DireccionDTO actualizada = direccionService.actualizarDireccion(id, direccionDTO);
        return ResponseEntity.ok(actualizada);
    }

    /**
     * Elimina una dirección por su UUID.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar dirección", description = "Elimina una dirección por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Dirección eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Dirección no encontrada")
    })
    public ResponseEntity<Void> eliminarDireccion(@PathVariable UUID id) {
        direccionService.eliminarDireccion(id);
        return ResponseEntity.noContent().build();
    }
}
