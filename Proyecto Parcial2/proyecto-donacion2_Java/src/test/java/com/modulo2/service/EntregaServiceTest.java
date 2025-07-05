package com.modulo2.service;

import com.modulo2.model.Donacion;
import com.modulo2.model.Entrega;
import com.modulo2.model.Receptor;
import com.modulo2.model.Solicitud;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class EntregaServiceTest {

@Autowired
private EntregaService entregaService;

@Autowired
private SolicitudService solicitudService;

@Autowired
private DonacionService donacionService;

@Autowired
private ReceptorService receptorService;

private Entrega entrega;

@BeforeEach
public void setUp() {
    // Crear receptor
    Receptor receptor = new Receptor();
    receptor.setNombre("María Gómez");
    receptor.setCedula("0987654321");
    receptor.setTelefono("0998888777");
    receptor.setDireccion("Av. Amazonas 456");
    receptor.setCorreo("maria@example.com");
    receptor = receptorService.guardar(receptor);

    // Crear solicitud
    Solicitud solicitud = new Solicitud();
    solicitud.setFechaSolicitud(LocalDate.now());
    solicitud.setEstado("Pendiente");
    solicitud.setObservacion("Solicitud prueba");
    solicitud.setReceptor(receptor);
    solicitud = solicitudService.guardar(solicitud);

    // Crear donación
    Donacion donacion = new Donacion();
    donacion.setDescripcion("Donación de alimentos");
    donacion = donacionService.guardar(donacion);

    // Crear entrega
    entrega = new Entrega();
    entrega.setFechaEntrega(LocalDate.now());
    entrega.setObservacion("Entrega inicial");
    entrega.setSolicitud(solicitud);
    entrega.setDonacion(donacion);

    entregaService.guardar(entrega);
}

@Test
public void testGuardarEntrega() {
    Entrega nueva = new Entrega();
    nueva.setFechaEntrega(LocalDate.now());
    nueva.setObservacion("Entrega adicional");
    nueva.setSolicitud(entrega.getSolicitud());
    nueva.setDonacion(entrega.getDonacion());

    Entrega guardada = entregaService.guardar(nueva);

    assertThat(guardada).isNotNull();
    assertThat(guardada.getId()).isNotNull();
    assertThat(guardada.getObservacion()).isEqualTo("Entrega adicional");
}

@Test
public void testObtenerPorId() {
    Entrega obtenida = entregaService.obtenerPorId(entrega.getId());
    assertThat(obtenida).isNotNull();
    assertThat(obtenida.getObservacion()).isEqualTo("Entrega inicial");
}

@Test
public void testObtenerTodas() {
    List<Entrega> lista = entregaService.obtenerTodas();
    assertThat(lista).isNotEmpty();
}

@Test
public void testEliminar() {
    Long id = entrega.getId();
    entregaService.eliminar(id);

    Entrega eliminada = entregaService.obtenerPorId(id);
    assertThat(eliminada).isNull();
}
}