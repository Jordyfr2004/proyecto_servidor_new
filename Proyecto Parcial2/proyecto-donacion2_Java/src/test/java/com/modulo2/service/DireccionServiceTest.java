package com.modulo2.service;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.modulo2.model.Direccion;
import com.modulo2.model.Receptor;

@SpringBootTest
@Transactional
public class DireccionServiceTest {

@Autowired
private DireccionService direccionService;

@Autowired
private ReceptorService receptorService;

private Receptor receptor;

@BeforeEach
public void setUp() {
    receptor = new Receptor();
    receptor.setNombre("Carlos");
    receptor.setCedula("1234567890");
    receptor.setTelefono("0999999999");
    receptor.setDireccion("Calle Principal");
    receptor.setCorreo("carlos@example.com");
    receptor = receptorService.guardar(receptor);
}

@Test
public void testGuardarDireccion() {
    Direccion direccion = new Direccion();
    direccion.setCalle("Av. Amazonas");
    direccion.setCiudad("Quito");
    direccion.setProvincia("Pichincha");
    direccion.setCodigoPostal("170123");
    direccion.setReceptor(receptor);

    Direccion guardada = direccionService.guardar(direccion);

    assertThat(guardada).isNotNull();
    assertThat(guardada.getId()).isNotNull();
    assertThat(guardada.getCiudad()).isEqualTo("Quito");
}

@Test
public void testObtenerPorId() {
    Direccion direccion = new Direccion();
    direccion.setCalle("Av. Quito");
    direccion.setCiudad("Guayaquil");
    direccion.setProvincia("Guayas");
    direccion.setCodigoPostal("090112");
    direccion.setReceptor(receptor);

    Direccion guardada = direccionService.guardar(direccion);

    Direccion obtenida = direccionService.obtenerPorId(guardada.getId());
    assertThat(obtenida).isNotNull();
    assertThat(obtenida.getCiudad()).isEqualTo("Guayaquil");
}

@Test
public void testEliminarDireccion() {
    Direccion direccion = new Direccion();
    direccion.setCalle("Calle 123");
    direccion.setCiudad("Cuenca");
    direccion.setProvincia("Azuay");
    direccion.setCodigoPostal("010101");
    direccion.setReceptor(receptor);

    Direccion guardada = direccionService.guardar(direccion);
    Long id = guardada.getId();

    direccionService.eliminar(id);
    Direccion eliminada = direccionService.obtenerPorId(id);

    assertThat(eliminada).isNull();
}

@Test
public void testObtenerTodasDirecciones() {
    Direccion direccion = new Direccion();
    direccion.setCalle("Av. 6 de Diciembre");
    direccion.setCiudad("Quito");
    direccion.setProvincia("Pichincha");
    direccion.setCodigoPostal("170456");
    direccion.setReceptor(receptor);

    direccionService.guardar(direccion);

    List<Direccion> lista = direccionService.obtenerTodas();
    assertThat(lista).isNotEmpty();
}
}