package com.abcretail.authentication_service.domain.exception;

/**
 * Thrown when a user attempts to access a resource
 * without proper authorization.
 */
public class UnauthorizedException extends AuthenticationException {

    public UnauthorizedException() {
        super("Unauthorized access.");
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

}