package com.abcretail.authentication_service.infrastructure.security.jwt;

import com.abcretail.authentication_service.domain.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final SecretKey secretKey;

    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        this.secretKey = Keys.hmacShaKeyFor(
                jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8)
        );
    }

    /**
     * Generate JWT Access Token.
     */
    public String generateAccessToken(User user) {

        Date issuedAt = new Date();

        Date expiration = new Date(
                issuedAt.getTime()
                        + jwtProperties.getAccessTokenExpiration()
        );

        return Jwts.builder()

                // Standard Claims
                .subject(String.valueOf(user.getId()))
                .issuer(jwtProperties.getIssuer())
                .issuedAt(issuedAt)
                .expiration(expiration)

                // Custom Claims
                .claim("email", user.getEmail())
                .claim("username", user.getUsername())
                .claim("role", user.getRole().name())
                .claim("status", user.getStatus().name())
                .claim("type", "ACCESS")

                // Signature
                .signWith(secretKey)
                .compact();
    }

    /**
     * Generate JWT Refresh Token.
     */
    public String generateRefreshToken(User user) {

        Date issuedAt = new Date();

        Date expiration = new Date(
                issuedAt.getTime()
                        + jwtProperties.getRefreshTokenExpiration()
        );

        return Jwts.builder()

                // Standard Claims
                .subject(String.valueOf(user.getId()))
                .issuer(jwtProperties.getIssuer())
                .issuedAt(issuedAt)
                .expiration(expiration)

                // Token Type
                .claim("type", "REFRESH")

                // Signature
                .signWith(secretKey)
                .compact();
    }

}