package com.abcretail.authentication_service.infrastructure.email;

import com.abcretail.authentication_service.application.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${application.frontend.base-url}")
    private String frontendBaseUrl;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendPasswordResetEmail(String to, String resetToken) {

        String resetLink =
                frontendBaseUrl + "/reset-password.html?token=" + resetToken;

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject("ABC Retail System - Password Reset");

        message.setText(
                "Hello,\n\n" +
                        "We received a request to reset your password.\n\n" +
                        "Click the link below to reset your password:\n\n" +
                        resetLink +
                        "\n\nThis link will expire in 30 minutes.\n\n" +
                        "If you did not request a password reset, please ignore this email.\n\n" +
                        "Regards,\n" +
                        "ABC Retail Team"
        );

        mailSender.send(message);
    }

    @Override
    public void sendVerificationEmail(String to, String verificationToken) {

        String verificationLink =
                frontendBaseUrl + "/verify-email.html?token=" + verificationToken;

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject("ABC Retail System - Verify Your Email");

        message.setText(
                "Welcome to ABC Retail!\n\n" +
                        "Please verify your email by clicking the link below:\n\n" +
                        verificationLink +
                        "\n\nIf you did not create this account, please ignore this email.\n\n" +
                        "Regards,\n" +
                        "ABC Retail Team"
        );

        mailSender.send(message);
    }
}