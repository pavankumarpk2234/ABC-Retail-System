package com.abcretail.authentication_service.application.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ForgotPasswordRequest {

    @NotBlank(message = "Email is required.")
    @Email(message = "Please enter a valid email address.")
    private String email;

    public ForgotPasswordRequest() {
    }

    public ForgotPasswordRequest(String email) {
        this.email = email;
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
}