package com.donacion.modulo2.service;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.donacion.modulo2.dto.DireccionDTO;
import com.donacion.modulo2.repository.DireccionRepository;

class DireccionServiceTest {
    @Mock
    private DireccionRepository direccionRepository;

    @InjectMocks
    private com.donacion.modulo2.service.impl.DireccionServiceImpl direccionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodasDirecciones() {
        when(direccionRepository.findAll()).thenReturn(Collections.emptyList());
        List<DireccionDTO> result = direccionService.obtenerTodas();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
