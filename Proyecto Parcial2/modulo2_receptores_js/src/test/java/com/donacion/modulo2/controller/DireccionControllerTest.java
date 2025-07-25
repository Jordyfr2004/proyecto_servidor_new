package com.donacion.modulo2.controller;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.donacion.modulo2.service.DireccionService;

class DireccionControllerTest {
    @Mock
    private DireccionService direccionService;

    @InjectMocks
    private DireccionController direccionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodasDirecciones() {
        when(direccionService.obtenerTodas()).thenReturn(Collections.emptyList());
        ResponseEntity<?> response = direccionController.obtenerTodas();
        assertEquals(200, response.getStatusCode().value());
    }
}
