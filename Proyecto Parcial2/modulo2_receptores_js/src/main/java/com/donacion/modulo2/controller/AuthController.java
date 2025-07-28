package com.donacion.modulo2.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donacion.modulo2.config.JwtUtil;

@RestController
@RequestMapping("/api/public")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    // Endpoint público para login (usuario y contraseña fijos de ejemplo)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        // Usuario y contraseña de ejemplo (ajusta según tu lógica real)
        if ("admin".equals(username) && "admin123".equals(password)) {
            String token = jwtUtil.generateToken(username);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Credenciales inválidas");
            return ResponseEntity.status(401).body(response);
        }
    }
}
