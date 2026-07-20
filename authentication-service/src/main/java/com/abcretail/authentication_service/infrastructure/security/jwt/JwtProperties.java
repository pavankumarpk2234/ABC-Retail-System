package com.abcretail.authentication_service.infrastructure.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * Secret key used to sign JWT tokens.
     */
    private String secret;

    /**
     * JWT issuer.
     */
    private String issuer;

    /**
     * Access Token expiration time in milliseconds.
     */
    private long accessTokenExpiration;

    /**
     * Refresh Token expiration time in milliseconds.
     */
    private long refreshTokenExpiration;

    public JwtProperties() {
    }

    // ===========================
    // Getters & Setters
    // ===========================

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public long getAccessTokenExpiration() {
        return accessTokenExpiration;
    }

    public void setAccessTokenExpiration(long accessTokenExpiration) {
        this.accessTokenExpiration = accessTokenExpiration;
    }

    public long getRefreshTokenExpiration() {
        return refreshTokenExpiration;
    }

    public void setRefreshTokenExpiration(long refreshTokenExpiration) {
        this.refreshTokenExpiration = refreshTokenExpiration;
    }

}