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
import org.springframework.web.bind.annotation.RequestParam;
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
                examples = @ExampleObject(value = "{ \"calle\": \"Av. 24 de Mayo y Calle J\", \"ciudad\": \"Manta\", \"provincia\": \"Manabí\", \"referencia\": \"Frente al parque central\", \"codigoPostal\": \"130802\", \"idReceptor\": \"xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx\" }")
            )
        )
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Dirección creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "404", description = "Receptor no encontrado"),
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
                examples = @ExampleObject(value = "{ \"calle\": \"Av. América y Calle 15\", \"ciudad\": \"Portoviejo\", \"provincia\": \"Manabí\", \"referencia\": \"Cerca del colegio San José\", \"codigoPostal\": \"130101\", \"idReceptor\": \"xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx\" }")
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

    /**
     * Obtiene todas las direcciones de un receptor específico.
     */
    @GetMapping("/receptor/{receptorId}")
    @Operation(summary = "Obtener direcciones por receptor", description = "Retorna todas las direcciones de un receptor específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Direcciones del receptor obtenidas correctamente"),
        @ApiResponse(responseCode = "404", description = "Receptor no encontrado")
    })
    public ResponseEntity<List<DireccionDTO>> obtenerDireccionesPorReceptor(@PathVariable UUID receptorId) {
        return ResponseEntity.ok(direccionService.obtenerDireccionesPorReceptor(receptorId));
    }

    /**
     * Obtiene direcciones por ciudad.
     */
    @GetMapping("/ciudad/{ciudad}")
    @Operation(summary = "Obtener direcciones por ciudad", description = "Retorna todas las direcciones de una ciudad específica.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Direcciones por ciudad obtenidas correctamente")
    })
    public ResponseEntity<List<DireccionDTO>> obtenerDireccionesPorCiudad(@PathVariable String ciudad) {
        return ResponseEntity.ok(direccionService.obtenerDireccionesPorCiudad(ciudad));
    }

    /**
     * Obtiene direcciones por provincia.
     */
    @GetMapping("/provincia/{provincia}")
    @Operation(summary = "Obtener direcciones por provincia", description = "Retorna todas las direcciones de una provincia específica.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Direcciones por provincia obtenidas correctamente")
    })
    public ResponseEntity<List<DireccionDTO>> obtenerDireccionesPorProvincia(@PathVariable String provincia) {
        return ResponseEntity.ok(direccionService.obtenerDireccionesPorProvincia(provincia));
    }

    /**
     * Obtiene la dirección principal de un receptor.
     */
    @GetMapping("/receptor/{receptorId}/principal")
    @Operation(summary = "Obtener dirección principal", description = "Retorna la dirección principal de un receptor específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Dirección principal obtenida correctamente"),
        @ApiResponse(responseCode = "404", description = "Dirección principal no encontrada")
    })
    public ResponseEntity<DireccionDTO> obtenerDireccionPrincipal(@PathVariable UUID receptorId) {
        DireccionDTO direccionPrincipal = direccionService.obtenerDireccionPrincipal(receptorId);
        if (direccionPrincipal != null) {
            return ResponseEntity.ok(direccionPrincipal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Marca una dirección como principal para un receptor.
     */
    @PutMapping("/{direccionId}/marcar-principal/{receptorId}")
    @Operation(summary = "Marcar dirección como principal", description = "Marca una dirección específica como principal para un receptor.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Dirección marcada como principal correctamente"),
        @ApiResponse(responseCode = "404", description = "Dirección o receptor no encontrado"),
        @ApiResponse(responseCode = "400", description = "La dirección no pertenece al receptor especificado")
    })
    public ResponseEntity<DireccionDTO> marcarComoPrincipal(@PathVariable UUID direccionId, @PathVariable UUID receptorId) {
        DireccionDTO direccionActualizada = direccionService.marcarComoPrincipal(direccionId, receptorId);
        return ResponseEntity.ok(direccionActualizada);
    }

    /**
     * Obtiene direcciones que tengan coordenadas geográficas.
     */
    @GetMapping("/con-coordenadas")
    @Operation(summary = "Obtener direcciones con coordenadas", description = "Retorna todas las direcciones que tienen coordenadas geográficas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Direcciones con coordenadas obtenidas correctamente")
    })
    public ResponseEntity<List<DireccionDTO>> obtenerDireccionesConCoordenadas() {
        return ResponseEntity.ok(direccionService.obtenerDireccionesConCoordenadas());
    }

    /**
     * Busca direcciones por texto.
     */
    @GetMapping("/buscar")
    @Operation(summary = "Buscar direcciones por texto", description = "Busca direcciones que contengan el texto especificado en cualquier campo.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Búsqueda realizada correctamente")
    })
    public ResponseEntity<List<DireccionDTO>> buscarDireccionesPorTexto(@RequestParam String texto) {
        return ResponseEntity.ok(direccionService.buscarDireccionesPorTexto(texto));
    }

    /**
     * Actualiza las coordenadas geográficas de una dirección.
     */
    @PutMapping("/{direccionId}/coordenadas")
    @Operation(summary = "Actualizar coordenadas", description = "Actualiza las coordenadas geográficas de una dirección.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Coordenadas actualizadas correctamente"),
        @ApiResponse(responseCode = "404", description = "Dirección no encontrada"),
        @ApiResponse(responseCode = "400", description = "Coordenadas inválidas")
    })
    public ResponseEntity<DireccionDTO> actualizarCoordenadas(
            @PathVariable UUID direccionId,
            @RequestParam Double latitud,
            @RequestParam Double longitud) {
        DireccionDTO direccionActualizada = direccionService.actualizarCoordenadas(direccionId, latitud, longitud);
        return ResponseEntity.ok(direccionActualizada);
    }

    /**
     * Cuenta el número de direcciones de un receptor.
     */
    @GetMapping("/receptor/{receptorId}/contar")
    @Operation(summary = "Contar direcciones por receptor", description = "Retorna el número de direcciones que tiene un receptor específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Conteo realizado correctamente")
    })
    public ResponseEntity<Long> contarDireccionesPorReceptor(@PathVariable UUID receptorId) {
        long count = direccionService.contarDireccionesPorReceptor(receptorId);
        return ResponseEntity.ok(count);
    }

    /**
     * Obtiene direcciones por ciudad y provincia.
     */
    @GetMapping("/ubicacion")
    @Operation(summary = "Obtener direcciones por ciudad y provincia", description = "Retorna direcciones filtradas por ciudad y provincia.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Direcciones filtradas obtenidas correctamente")
    })
    public ResponseEntity<List<DireccionDTO>> obtenerDireccionesPorCiudadYProvincia(
            @RequestParam String ciudad,
            @RequestParam String provincia) {
        return ResponseEntity.ok(direccionService.obtenerDireccionesPorCiudadYProvincia(ciudad, provincia));
    }
}
