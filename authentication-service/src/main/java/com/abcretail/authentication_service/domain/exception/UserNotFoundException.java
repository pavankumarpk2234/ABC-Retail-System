package com.abcretail.authentication_service.domain.exception;

/**
 * Thrown when the requested user cannot be found.
 */
public class UserNotFoundException extends AuthenticationException {

    public UserNotFoundException() {
        super("User not found.");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}