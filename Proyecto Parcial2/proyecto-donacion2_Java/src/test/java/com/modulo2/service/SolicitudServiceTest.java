package com.modulo2.service;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.modulo2.model.Receptor;
import com.modulo2.model.Solicitud;

@SpringBootTest
@Transactional
public class SolicitudServiceTest {

@Autowired
private SolicitudService solicitudService;

@Autowired
private ReceptorService receptorService;

private Solicitud solicitud;

@BeforeEach
public void setUp() {
    // Crear un receptor necesario para la solicitud
    Receptor receptor = new Receptor();
    receptor.setNombre("Juan Pérez");
    receptor.setCedula("1234567890");
    receptor.setTelefono("0999999999");
    receptor.setDireccion("Calle Falsa 123");
    receptor.setCorreo("juan@example.com");
    receptor = receptorService.guardar(receptor);

    solicitud = new Solicitud();
    solicitud.setFechaSolicitud(LocalDate.now());
    solicitud.setEstado("Pendiente");
    solicitud.setObservacion("Revisión médica");
    solicitud.setReceptor(receptor);

    solicitudService.guardar(solicitud);
}

@Test
public void testGuardarSolicitud() {
    Solicitud nueva = new Solicitud();
    nueva.setFechaSolicitud(LocalDate.now());
    nueva.setEstado("Aceptada");
    nueva.setObservacion("Aprobada por el sistema");
    nueva.setReceptor(solicitud.getReceptor());

    Solicitud guardada = solicitudService.guardar(nueva);

    assertThat(guardada).isNotNull();
    assertThat(guardada.getId()).isNotNull();
    assertThat(guardada.getEstado()).isEqualTo("Aceptada");
}

@Test
public void testObtenerPorId() {
    Solicitud obtenida = solicitudService.obtenerPorId(solicitud.getId());
    assertThat(obtenida).isNotNull();
    assertThat(obtenida.getEstado()).isEqualTo("Pendiente");
}

@Test
public void testObtenerTodas() {
    List<Solicitud> lista = solicitudService.obtenerTodas();
    assertThat(lista).isNotEmpty();
}

@Test
public void testEliminar() {
    Long id = solicitud.getId();
    solicitudService.eliminar(id);

    Solicitud eliminada = solicitudService.obtenerPorId(id);
    assertThat(eliminada).isNull();
}
}

