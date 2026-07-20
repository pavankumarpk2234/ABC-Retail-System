package com.abcretail.authentication_service.domain.exception;

/**
 * Thrown when the provided password reset token
 * is invalid or does not exist.
 */
public class InvalidPasswordResetTokenException extends AuthenticationException {

    public InvalidPasswordResetTokenException() {
        super("Invalid password reset token.");
    }

    public InvalidPasswordResetTokenException(String message) {
        super(message);
    }

    public InvalidPasswordResetTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPasswordResetTokenException(Throwable cause) {
        super(cause);
    }

}