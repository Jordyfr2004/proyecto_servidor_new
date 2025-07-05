package com.modulo2.service;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.modulo2.model.HistorialReceptor;
import com.modulo2.model.Receptor;

@SpringBootTest
@Transactional
public class HistorialReceptorServiceTest {

@Autowired
private HistorialReceptorService historialService;

@Autowired
private ReceptorService receptorService;

private Receptor receptor;

@BeforeEach
public void setUp() {
    receptor = new Receptor();
    receptor.setNombre("Usuario Test");
    receptor.setCedula("1111111111");
    receptor.setTelefono("0980000000");
    receptor.setDireccion("Calle de prueba");
    receptor.setCorreo("test@correo.com");
    receptor = receptorService.guardar(receptor);
}

@Test
public void testGuardarHistorial() {
    HistorialReceptor historial = new HistorialReceptor();
    historial.setFechaRegistro(LocalDate.now());
    historial.setDescripcion("Ingreso normal");
    historial.setReceptor(receptor);

    HistorialReceptor guardado = historialService.guardar(historial);

    assertThat(guardado).isNotNull();
    assertThat(guardado.getId()).isNotNull();
    assertThat(guardado.getReceptor().getId()).isEqualTo(receptor.getId());
}

@Test
public void testObtenerPorId() {
    HistorialReceptor historial = new HistorialReceptor();
    historial.setFechaRegistro(LocalDate.now());
    historial.setDescripcion("Chequeo de rutina");
    historial.setReceptor(receptor);
    HistorialReceptor guardado = historialService.guardar(historial);

    HistorialReceptor obtenido = historialService.obtenerPorId(guardado.getId());

    assertThat(obtenido).isNotNull();
    assertThat(obtenido.getDescripcion()).isEqualTo("Chequeo de rutina");
}

@Test
public void testEliminarHistorial() {
    HistorialReceptor historial = new HistorialReceptor();
    historial.setFechaRegistro(LocalDate.now());
    historial.setDescripcion("Historial temporal");
    historial.setReceptor(receptor);

    HistorialReceptor guardado = historialService.guardar(historial);
    Long id = guardado.getId();

    historialService.eliminar(id);
    HistorialReceptor eliminado = historialService.obtenerPorId(id);

    assertThat(eliminado).isNull();
}

@Test
public void testObtenerTodos() {
    HistorialReceptor historial = new HistorialReceptor();
    historial.setFechaRegistro(LocalDate.now());
    historial.setDescripcion("Observación");
    historial.setReceptor(receptor);

    historialService.guardar(historial);

    List<HistorialReceptor> lista = historialService.obtenerTodos();

    assertThat(lista).isNotEmpty();
}

@Test
public void testEvaluarPrioridad() {
    // Sin registros
    String prioridadVacia = historialService.evaluarPrioridad(receptor.getId());
    assertThat(prioridadVacia).contains("Sin prioridad");

    // Con registro "urgente"
    HistorialReceptor urgente = new HistorialReceptor();
    urgente.setFechaRegistro(LocalDate.now());
    urgente.setDescripcion("caso urgente");
    urgente.setReceptor(receptor);
    historialService.guardar(urgente);

    String prioridadAlta = historialService.evaluarPrioridad(receptor.getId());
    assertThat(prioridadAlta).contains("Alta prioridad");
}
}