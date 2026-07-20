package com.abcretail.authentication_service.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ChangePasswordRequest {

    @NotBlank(message = "Current password is required.")
    private String currentPassword;

    @NotBlank(message = "New password is required.")
    @Size(min = 8, message = "New password must be at least 8 characters long.")
    private String newPassword;

    public ChangePasswordRequest() {
    }

    public ChangePasswordRequest(String currentPassword,
                                 String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    // ===========================
    // Getters & Setters
    // ===========================

    /**
     * User's current password.
     */
    public String getCurrentPassword() {
        return currentPassword;
    }

    /**
     * User's current password.
     */
    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    /**
     * User's new password.
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * User's new password.
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}