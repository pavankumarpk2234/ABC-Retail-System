package com.abcretail.authentication_service.application.usecase.forgotpassword;

import com.abcretail.authentication_service.application.dto.request.ForgotPasswordRequest;
import com.abcretail.authentication_service.application.dto.response.ForgotPasswordResponse;
import com.abcretail.authentication_service.application.service.EmailService;
import com.abcretail.authentication_service.domain.model.User;
import com.abcretail.authentication_service.domain.repository.UserRepository;
import com.abcretail.authentication_service.infrastructure.security.token.TokenGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ForgotPasswordService implements ForgotPasswordUseCase {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final TokenGenerator tokenGenerator;

    public ForgotPasswordService(UserRepository userRepository,
                                 EmailService emailService,
                                 TokenGenerator tokenGenerator) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public ForgotPasswordResponse forgotPassword(
            ForgotPasswordRequest request) {

        Optional<User> optionalUser =
                userRepository.findByEmail(request.getEmail());

        /*
         * Always return the same response whether
         * the email exists or not.
         */
        if (optionalUser.isEmpty()) {

            return new ForgotPasswordResponse(
                    "If an account exists with the provided email, " +
                            "password reset instructions have been sent."
            );
        }

        User user = optionalUser.get();

        // Generate secure reset token
        String resetToken = tokenGenerator.generateToken();

        // Store token and expiry (30 minutes)
        user.setPasswordResetToken(resetToken);
        user.setPasswordResetTokenExpiry(
                LocalDateTime.now().plusMinutes(30)
        );

        // Save updated user
        userRepository.save(user);

        // Send email
        emailService.sendPasswordResetEmail(
                user.getEmail(),
                resetToken
        );

        // Generic response
        return new ForgotPasswordResponse(
                "If an account exists with the provided email, " +
                        "password reset instructions have been sent."
        );
    }

}