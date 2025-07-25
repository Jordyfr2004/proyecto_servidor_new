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

import com.donacion.modulo2.service.ReceptorService;

class ReceptorControllerTest {
    @Mock
    private ReceptorService receptorService;

    @InjectMocks
    private ReceptorController receptorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodosReceptores() {
        when(receptorService.obtenerTodos()).thenReturn(Collections.emptyList());
        ResponseEntity<?> response = receptorController.obtenerTodos();
        assertEquals(200, response.getStatusCode().value());
    }
}
