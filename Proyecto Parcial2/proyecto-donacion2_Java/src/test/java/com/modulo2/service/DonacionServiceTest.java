package com.modulo2.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.modulo2.model.Donacion;

@SpringBootTest
@Transactional
public class DonacionServiceTest {

@Autowired
private DonacionService donacionService;

private Donacion donacion;

@BeforeEach
public void setUp() {
    donacion = new Donacion();
    donacion.setDescripcion("Donación de alimentos");
    donacion = donacionService.guardar(donacion);
}

@Test
public void testGuardarDonacion() {
    Donacion nueva = new Donacion();
    nueva.setDescripcion("Donación de ropa");

    Donacion guardada = donacionService.guardar(nueva);

    assertThat(guardada).isNotNull();
    assertThat(guardada.getId()).isNotNull();
    assertThat(guardada.getDescripcion()).isEqualTo("Donación de ropa");
}

@Test
public void testObtenerPorId() {
    Donacion obtenida = donacionService.obtenerPorId(donacion.getId());
    assertThat(obtenida).isNotNull();
    assertThat(obtenida.getDescripcion()).isEqualTo("Donación de alimentos");
}

@Test
public void testDonacionNoExistente() {
    Donacion donacionNull = donacionService.obtenerPorId(9999L); // id que no existe
    assertThat(donacionNull).isNull();
}
}