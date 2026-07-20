package com.abcretail.authentication_service.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {

    private Long id;
    private String username;
    private String email;
    private String password;
    private Role role;
    private UserStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Forgot Password
    private String passwordResetToken;
    private LocalDateTime passwordResetTokenExpiry;

    public User() {
    }

    public User(Long id,
                String username,
                String email,
                String password,
                Role role,
                UserStatus status,
                LocalDateTime createdAt,
                LocalDateTime updatedAt,
                String passwordResetToken,
                LocalDateTime passwordResetTokenExpiry) {

        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.passwordResetToken = passwordResetToken;
        this.passwordResetTokenExpiry = passwordResetTokenExpiry;
    }

    // ===========================
    // Business Methods
    // ===========================

    public boolean isActive() {
        return this.status == UserStatus.ACTIVE;
    }

    public boolean isLocked() {
        return this.status == UserStatus.LOCKED;
    }

    public boolean isDeleted() {
        return this.status == UserStatus.DELETED;
    }

    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }

    public boolean isCustomer() {
        return this.role == Role.CUSTOMER;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void changeRole(Role newRole) {
        this.role = newRole;
    }

    public void changeStatus(UserStatus newStatus) {
        this.status = newStatus;
    }

    /**
     * Store password reset token and its expiry.
     */
    public void generatePasswordResetToken(String token,
                                           LocalDateTime expiry) {
        this.passwordResetToken = token;
        this.passwordResetTokenExpiry = expiry;
    }

    /**
     * Remove password reset token after successful password reset.
     */
    public void clearPasswordResetToken() {
        this.passwordResetToken = null;
        this.passwordResetTokenExpiry = null;
    }

    /**
     * Check whether the reset token has expired.
     */
    public boolean isPasswordResetTokenExpired() {

        return passwordResetTokenExpiry == null
                || passwordResetTokenExpiry.isBefore(LocalDateTime.now());
    }

    // ===========================
    // Getters & Setters
    // ===========================

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

    /**
     * Returns the user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the hashed password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Password must always be stored in hashed form.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public LocalDateTime getPasswordResetTokenExpiry() {
        return passwordResetTokenExpiry;
    }

    public void setPasswordResetTokenExpiry(
            LocalDateTime passwordResetTokenExpiry) {
        this.passwordResetTokenExpiry = passwordResetTokenExpiry;
    }

    // ===========================
    // equals & hashCode
    // ===========================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // ===========================
    // toString
    // ===========================

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}