package com.modulo2.security;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApiKeyAuthFilter extends OncePerRequestFilter {

private final String apiKeyHeaderName = "X-API-KEY";
private final String expectedApiKey;

public ApiKeyAuthFilter(String expectedApiKey) {
    this.expectedApiKey = expectedApiKey;
}

@Override
protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

    String requestApiKey = request.getHeader(apiKeyHeaderName);

    if (expectedApiKey.equals(requestApiKey)) {
        filterChain.doFilter(request, response);
    } else {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"Unauthorized - Invalid API Key\"}");
    }
}

@Override
protected boolean shouldNotFilter(HttpServletRequest request) {
    String path = request.getRequestURI();
    return path.startsWith("/swagger-ui")
            || path.startsWith("/v3/api-docs")
            || path.startsWith("/h2-console");
}
}