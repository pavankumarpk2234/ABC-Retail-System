package com.abcretail.authentication_service.application.dto.response;

import java.time.LocalDateTime;

public class RegisterResponse {

    private Long id;
    private String username;
    private String email;
    private String role;
    private LocalDateTime registeredAt;

    public RegisterResponse() {
    }

    public RegisterResponse(Long id,
                            String username,
                            String email,
                            String role,
                            LocalDateTime registeredAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.registeredAt = registeredAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

}