package com.modulo2.service;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.modulo2.model.Receptor;

@SpringBootTest
@Transactional
public class ReceptorServiceTest {

@Autowired
private ReceptorService receptorService;


private Receptor receptor;

@BeforeEach
public void setUp() {
    receptor = new Receptor();
    receptor.setNombre("Juan Pérez");
    receptor.setCedula("1234567890");
    receptor.setTelefono("0999999999");
    receptor.setDireccion("Calle Falsa 123");
    receptor.setCorreo("juan@example.com");

    receptorService.guardar(receptor);
}

@Test
public void testGuardarReceptor() {
    Receptor nuevo = new Receptor();
    nuevo.setNombre("Ana María");
    nuevo.setCedula("0987654321");
    nuevo.setTelefono("0981111222");
    nuevo.setDireccion("Av. Principal 456");
    nuevo.setCorreo("ana@example.com");

    Receptor guardado = receptorService.guardar(nuevo);

    assertThat(guardado).isNotNull();
    assertThat(guardado.getId()).isNotNull();
    assertThat(guardado.getNombre()).isEqualTo("Ana María");
}

@Test
public void testObtenerPorId() {
    Receptor obtenido = receptorService.obtenerPorId(receptor.getId());
    assertThat(obtenido).isNotNull();
    assertThat(obtenido.getNombre()).isEqualTo("Juan Pérez");
}

@Test
public void testObtenerTodos() {
    List<Receptor> lista = receptorService.obtenerTodos();
    assertThat(lista).isNotEmpty();
}

@Test
public void testEliminar() {
    Long id = receptor.getId();
    receptorService.eliminar(id);

    Receptor eliminado = receptorService.obtenerPorId(id);
    assertThat(eliminado).isNull();
}
}