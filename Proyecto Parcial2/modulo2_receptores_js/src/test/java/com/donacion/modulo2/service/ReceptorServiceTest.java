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

import com.donacion.modulo2.dto.ReceptorDTO;
import com.donacion.modulo2.repository.ReceptorRepository;

class ReceptorServiceTest {
    @Mock
    private ReceptorRepository receptorRepository;

    @InjectMocks
    private com.donacion.modulo2.service.impl.ReceptorServiceImpl receptorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodosReceptores() {
        when(receptorRepository.findAll()).thenReturn(Collections.emptyList());
        List<ReceptorDTO> result = receptorService.obtenerTodos();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
