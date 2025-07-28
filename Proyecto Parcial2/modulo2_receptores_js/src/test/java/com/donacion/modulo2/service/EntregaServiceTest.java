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

import com.donacion.modulo2.dto.EntregaDTO;
import com.donacion.modulo2.repository.EntregaRepository;

class EntregaServiceTest {
    @Mock
    private EntregaRepository entregaRepository;

    @InjectMocks
    private com.donacion.modulo2.service.impl.EntregaServiceImpl entregaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodasEntregas() {
        when(entregaRepository.findAll()).thenReturn(Collections.emptyList());
        List<EntregaDTO> result = entregaService.obtenerTodas();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
