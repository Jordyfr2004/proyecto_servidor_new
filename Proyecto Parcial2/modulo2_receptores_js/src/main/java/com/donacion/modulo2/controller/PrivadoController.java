package com.donacion.modulo2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/privado")
public class PrivadoController {

    // Endpoint protegido, solo accesible con JWT válido
    @GetMapping("/hello")
    public String helloPrivado() {
        return "¡Hola! Acceso permitido solo con JWT válido.";
    }
}
