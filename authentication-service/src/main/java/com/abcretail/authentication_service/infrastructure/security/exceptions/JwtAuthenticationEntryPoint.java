package com.abcretail.authentication_service.infrastructure.security.exceptions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * Called whenever an unauthenticated user tries to access
     * a protected resource.
     *
     * Example:
     * GET /api/v1/auth/test
     * Authorization header is missing or JWT is invalid.
     *
     * Response:
     * HTTP 401 Unauthorized
     */
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        response.getWriter().write("""
                {
                  "status":401,
                  "error":"Unauthorized",
                  "message":"Authentication is required to access this resource."
                }
                """);
    }
}