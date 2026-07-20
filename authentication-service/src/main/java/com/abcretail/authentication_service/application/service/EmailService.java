package com.abcretail.authentication_service.application.service;

public interface EmailService {

    /**
     * Sends a password reset email containing
     * a password reset link.
     *
     * @param to Recipient email address
     * @param resetToken Password reset token
     */
    void sendPasswordResetEmail(String to, String resetToken);

    /**
     * Sends an email verification email containing
     * an account verification link.
     *
     * @param to Recipient email address
     * @param verificationToken Email verification token
     */
    void sendVerificationEmail(String to, String verificationToken);

}