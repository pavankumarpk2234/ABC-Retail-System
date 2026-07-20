package com.abcretail.authentication_service.application.dto.response;

public class LoginResponse {

    private Long userId;
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Long accessTokenExpiresIn;
    private Long refreshTokenExpiresIn;

    public LoginResponse() {
    }

    public LoginResponse(Long userId,
                         String accessToken,
                         String refreshToken,
                         String tokenType,
                         Long accessTokenExpiresIn,
                         Long refreshTokenExpiresIn) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }

    // ===========================
    // Getters & Setters
    // ===========================

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * JWT Refresh Token.
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * JWT Refresh Token.
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * Usually "Bearer".
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * Usually "Bearer".
     */
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    /**
     * Access Token expiration in milliseconds.
     */
    public Long getAccessTokenExpiresIn() {
        return accessTokenExpiresIn;
    }

    /**
     * Access Token expiration in milliseconds.
     */
    public void setAccessTokenExpiresIn(Long accessTokenExpiresIn) {
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }

    /**
     * Refresh Token expiration in milliseconds.
     */
    public Long getRefreshTokenExpiresIn() {
        return refreshTokenExpiresIn;
    }

    /**
     * Refresh Token expiration in milliseconds.
     */
    public void setRefreshTokenExpiresIn(Long refreshTokenExpiresIn) {
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }

}