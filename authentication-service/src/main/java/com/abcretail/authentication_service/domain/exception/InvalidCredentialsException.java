package com.abcretail.authentication_service.domain.exception;

/**
 * Thrown when the provided login credentials are invalid.
 */
public class InvalidCredentialsException extends AuthenticationException {

    public InvalidCredentialsException() {
        super("Invalid email or password.");
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }

    public InvalidCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

}