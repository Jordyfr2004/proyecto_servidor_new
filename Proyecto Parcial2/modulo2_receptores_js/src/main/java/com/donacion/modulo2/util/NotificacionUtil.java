package com.donacion.modulo2.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NotificacionUtil {
    private static final String GATEWAY_URL = "http://localhost:4000/notificar"; // Cambia el puerto si tu gateway usa otro

    public void enviarNotificacion(String mensaje) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, String> body = new HashMap<>();
            body.put("mensaje", mensaje);
            ResponseEntity<String> response = restTemplate.postForEntity(GATEWAY_URL, body, String.class);
            // Puedes loguear la respuesta si lo deseas
        } catch (Exception e) {
            // Manejo simple: solo loguea el error, no interrumpe la ejecución
            System.err.println("Error enviando notificación al gateway: " + e.getMessage());
        }
    }
}
