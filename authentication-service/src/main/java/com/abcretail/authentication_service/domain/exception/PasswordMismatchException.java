package com.abcretail.authentication_service.domain.exception;

/**
 * Thrown when the new password and confirm password do not match.
 */
public class PasswordMismatchException extends AuthenticationException {

    public PasswordMismatchException() {
        super("New password and confirm password do not match.");
    }

    public PasswordMismatchException(String message) {
        super(message);
    }

    public PasswordMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordMismatchException(Throwable cause) {
        super(cause);
    }

}