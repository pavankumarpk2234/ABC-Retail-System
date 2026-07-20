package com.abcretail.authentication_service.domain.exception;

/**
 * Thrown when the password reset token has expired.
 */
public class PasswordResetTokenExpiredException extends AuthenticationException {

    public PasswordResetTokenExpiredException() {
        super("Password reset token has expired.");
    }

    public PasswordResetTokenExpiredException(String message) {
        super(message);
    }

    public PasswordResetTokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordResetTokenExpiredException(Throwable cause) {
        super(cause);
    }

}