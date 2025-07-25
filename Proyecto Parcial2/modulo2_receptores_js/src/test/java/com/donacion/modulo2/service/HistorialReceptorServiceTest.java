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

import com.donacion.modulo2.dto.HistorialReceptorDTO;
import com.donacion.modulo2.repository.HistorialReceptorRepository;

class HistorialReceptorServiceTest {
    @Mock
    private HistorialReceptorRepository historialReceptorRepository;

    @InjectMocks
    private com.donacion.modulo2.service.impl.HistorialReceptorServiceImpl historialReceptorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodosHistoriales() {
        when(historialReceptorRepository.findAll()).thenReturn(Collections.emptyList());
        List<HistorialReceptorDTO> result = historialReceptorService.obtenerTodos();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
