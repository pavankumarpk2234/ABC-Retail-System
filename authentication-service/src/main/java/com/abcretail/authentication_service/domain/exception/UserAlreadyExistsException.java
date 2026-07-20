package com.abcretail.authentication_service.domain.exception;

/**
 * Thrown when attempting to register a user with an email
 * or username that already exists.
 */
public class UserAlreadyExistsException extends AuthenticationException {

    public UserAlreadyExistsException() {
        super("User already exists.");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}