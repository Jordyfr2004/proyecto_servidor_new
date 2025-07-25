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

import com.donacion.modulo2.dto.SolicitudDTO;
import com.donacion.modulo2.repository.SolicitudRepository;

class SolicitudServiceTest {
    @Mock
    private SolicitudRepository solicitudRepository;

    @InjectMocks
    private com.donacion.modulo2.service.impl.SolicitudServiceImpl solicitudService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodasSolicitudes() {
        when(solicitudRepository.findAll()).thenReturn(Collections.emptyList());
        List<SolicitudDTO> result = solicitudService.obtenerTodas();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
