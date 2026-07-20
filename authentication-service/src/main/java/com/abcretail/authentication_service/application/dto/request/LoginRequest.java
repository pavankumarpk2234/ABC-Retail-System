package com.abcretail.authentication_service.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    /**
     * Can be either:
     * - Email (e.g. pavan@example.com)
     * - Username (e.g. pavan)
     */
    @NotBlank(message = "Email or Username is required.")
    private String login;

    /**
     * Plain text password received from client.
     * It will be compared with the encrypted password stored in the database.
     */
    @NotBlank(message = "Password is required.")
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    // ===========================
    // Getters & Setters
    // ===========================

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Plain text password received from client.
     * It will be compared with the encrypted password stored in the database.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Plain text password received from client.
     */
    public void setPassword(String password) {
        this.password = password;
    }

}