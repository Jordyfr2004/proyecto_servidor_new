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

import com.donacion.modulo2.service.HistorialReceptorService;

class HistorialReceptorControllerTest {
    @Mock
    private HistorialReceptorService historialReceptorService;

    @InjectMocks
    private HistorialReceptorController historialReceptorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodosHistoriales() {
        when(historialReceptorService.obtenerTodos()).thenReturn(Collections.emptyList());
        ResponseEntity<?> response = historialReceptorController.obtenerTodos();
        assertEquals(200, response.getStatusCode().value());
    }
}
