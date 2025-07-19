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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.donacion.modulo2.dto.ReceptorDTO;
import com.donacion.modulo2.service.ReceptorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

/**
 * ReceptorController:
 * Controlador REST para gestionar las operaciones CRUD de los receptores.
 */
@RestController
@RequestMapping("/api/receptores")
@CrossOrigin(origins = "*")
public class ReceptorController {

    @Autowired
    private ReceptorService receptorService;

    @Operation(
        summary = "Crear un nuevo receptor",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del nuevo receptor",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ReceptorDTO.class),
                examples = @ExampleObject(value = "{ \"nombre\": \"Juan Pérez\", \"cedula\": \"0102030405\", \"telefono\": \"0987654321\", \"direccion\": \"Av. Principal\", \"correo\": \"juan@example.com\" }")
            )
        )
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receptor creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos enviados"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<ReceptorDTO> crearReceptor(@Valid @RequestBody ReceptorDTO receptorDTO) {
        ReceptorDTO creado = receptorService.crearReceptor(receptorDTO);
        return ResponseEntity.ok(creado);
    }

    @Operation(summary = "Obtener todos los receptores registrados", description = "Devuelve la lista de todos los receptores registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de receptores obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<List<ReceptorDTO>> obtenerTodos() {
        return ResponseEntity.ok(receptorService.obtenerTodos());
    }

    @Operation(summary = "Buscar receptor por ID", description = "Busca un receptor por su identificador UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receptor encontrado"),
        @ApiResponse(responseCode = "404", description = "Receptor no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ReceptorDTO> obtenerPorId(@PathVariable UUID id) {
        ReceptorDTO dto = receptorService.obtenerPorId(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(
        summary = "Actualizar receptor por ID",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos actualizados del receptor",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ReceptorDTO.class),
                examples = @ExampleObject(value = "{ \"nombre\": \"Ana Torres\", \"cedula\": \"1102233445\", \"telefono\": \"0981234567\", \"direccion\": \"Calle 10\", \"correo\": \"ana@example.com\" }")
            )
        )
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Receptor actualizado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos enviados"),
        @ApiResponse(responseCode = "404", description = "Receptor no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ReceptorDTO> actualizarReceptor(
            @PathVariable UUID id,
            @Valid @RequestBody ReceptorDTO receptorDTO) {
        ReceptorDTO actualizado = receptorService.actualizarReceptor(id, receptorDTO);
        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Eliminar receptor por ID", description = "Elimina un receptor por su identificador UUID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Receptor eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Receptor no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReceptor(@PathVariable UUID id) {
        receptorService.eliminarReceptor(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Verificar si un correo ya existe", description = "Valida si un correo electrónico ya está registrado en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Resultado de la verificación (true o false)")
    })
    @GetMapping("/existe-correo")
    public ResponseEntity<Boolean> existePorCorreo(@RequestParam String correo) {
        boolean existe = receptorService.existePorCorreo(correo);
        return ResponseEntity.ok(existe);
    }
}
