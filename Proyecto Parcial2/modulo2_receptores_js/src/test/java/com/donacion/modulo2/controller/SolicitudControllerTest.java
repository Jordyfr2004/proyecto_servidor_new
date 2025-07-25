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

import com.donacion.modulo2.service.SolicitudService;

class SolicitudControllerTest {
    @Mock
    private SolicitudService solicitudService;

    @InjectMocks
    private SolicitudController solicitudController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodasSolicitudes() {
        when(solicitudService.obtenerTodas()).thenReturn(Collections.emptyList());
        ResponseEntity<?> response = solicitudController.obtenerTodas();
        assertEquals(200, response.getStatusCode().value());
    }
}
