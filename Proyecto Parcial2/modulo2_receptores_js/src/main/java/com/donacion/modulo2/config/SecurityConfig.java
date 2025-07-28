package com.donacion.modulo2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactiva CSRF para APIs REST
            .authorizeHttpRequests(auth -> auth
                // Rutas públicas (ajusta según tus necesidades)
                .requestMatchers(
                    "/api/public/**", // Endpoints públicos
                    "/swagger-ui/**", // Swagger UI
                    "/v3/api-docs/**", // Documentación OpenAPI
                    "/websocket/**", // Websockets
                    "/api/auth/**" // Login y registro públicos
                ).permitAll()
                // Todas las demás rutas requieren autenticación
                .anyRequest().authenticated()
            )
            // Agrega el filtro JWT antes del filtro de autenticación por usuario/contraseña
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
