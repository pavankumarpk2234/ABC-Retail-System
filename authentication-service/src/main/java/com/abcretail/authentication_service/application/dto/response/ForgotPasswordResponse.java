package com.abcretail.authentication_service.application.dto.response;

public class ForgotPasswordResponse {

    private String message;

    public ForgotPasswordResponse() {
    }

    public ForgotPasswordResponse(String message) {
        this.message = message;
    }

    /**
     * Returns the response message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the response message.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}