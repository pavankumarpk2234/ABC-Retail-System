package com.abcretail.authentication_service.presentation.exception;

import org.springframework.http.HttpStatus;

public enum ApiError {

    USER_ALREADY_EXISTS(
            HttpStatus.CONFLICT,
            "User already exists."
    ),

    USER_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "User not found."
    ),

    INVALID_CREDENTIALS(
            HttpStatus.UNAUTHORIZED,
            "Invalid email or password."
    ),

    UNAUTHORIZED(
            HttpStatus.FORBIDDEN,
            "Unauthorized access."
    ),

    VALIDATION_FAILED(
            HttpStatus.BAD_REQUEST,
            "Validation failed."
    ),

    INTERNAL_SERVER_ERROR(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "An unexpected error occurred."
    );

    private final HttpStatus httpStatus;
    private final String message;

    ApiError(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

}