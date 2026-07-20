package com.abcretail.authentication_service.infrastructure.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Component
public class JwtTokenValidator {

    private final SecretKey secretKey;

    public JwtTokenValidator(JwtProperties jwtProperties) {
        this.secretKey = Keys.hmacShaKeyFor(
                jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8)
        );
    }

    /**
     * Validate Access Token.
     */
    public boolean validateAccessToken(String token) {

        try {

            Claims claims = getClaims(token);

            return "ACCESS".equals(
                    claims.get("type", String.class)
            );

        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * Validate Refresh Token.
     */
    public boolean validateRefreshToken(String token) {

        try {

            Claims claims = getClaims(token);

            return "REFRESH".equals(
                    claims.get("type", String.class)
            );

        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * Extract User ID from JWT Subject.
     */
    public Long getUserId(String token) {
        return Long.parseLong(getClaims(token).getSubject());
    }

    /**
     * Extract email.
     */
    public String getEmail(String token) {
        return getClaims(token).get("email", String.class);
    }

    /**
     * Extract username.
     */
    public String getUsername(String token) {
        return getClaims(token).get("username", String.class);
    }

    /**
     * Extract role.
     */
    public String getRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    /**
     * Extract status.
     */
    public String getStatus(String token) {
        return getClaims(token).get("status", String.class);
    }

    /**
     * Extract token type.
     */
    public String getTokenType(String token) {
        return getClaims(token).get("type", String.class);
    }

    /**
     * Parse JWT and return claims.
     */
    private Claims getClaims(String token) {

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}