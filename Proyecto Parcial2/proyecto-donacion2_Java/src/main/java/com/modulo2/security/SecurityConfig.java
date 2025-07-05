package com.modulo2.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

@Value("${security.api.key}")
private String apiKey;

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/h2-console/**").permitAll()
            .anyRequest().authenticated()
        )
        .addFilterBefore(new ApiKeyAuthFilter(apiKey), BasicAuthenticationFilter.class)
        .headers(headers -> headers.frameOptions(frame -> frame.disable())); // Permitir H2 Console

    return http.build();
}
}