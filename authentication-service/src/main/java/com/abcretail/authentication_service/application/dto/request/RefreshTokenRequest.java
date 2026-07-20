package com.abcretail.authentication_service.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public class RefreshTokenRequest {

    @NotBlank(message = "Refresh token is required.")
    private String refreshToken;

    public RefreshTokenRequest() {
    }

    public RefreshTokenRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    // ===========================
    // Getters & Setters
    // ===========================

    /**
     * Refresh token issued during login.
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * Refresh token issued during login.
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}