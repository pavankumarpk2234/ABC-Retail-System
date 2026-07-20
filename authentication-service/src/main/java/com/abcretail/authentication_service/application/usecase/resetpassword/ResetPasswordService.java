package com.abcretail.authentication_service.application.usecase.resetpassword;

import com.abcretail.authentication_service.application.dto.request.ResetPasswordRequest;
import com.abcretail.authentication_service.application.dto.response.ResetPasswordResponse;
import com.abcretail.authentication_service.domain.exception.InvalidPasswordResetTokenException;
import com.abcretail.authentication_service.domain.exception.PasswordMismatchException;
import com.abcretail.authentication_service.domain.exception.PasswordResetTokenExpiredException;
import com.abcretail.authentication_service.domain.model.User;
import com.abcretail.authentication_service.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ResetPasswordService implements ResetPasswordUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResetPasswordService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Reset user's password using a valid password reset token.
     */
    @Override
    public ResetPasswordResponse resetPassword(
            ResetPasswordRequest request) {

        User user = userRepository
                .findByPasswordResetToken(request.getToken())
                .orElseThrow(InvalidPasswordResetTokenException::new);

        if (user.isPasswordResetTokenExpired()) {
            throw new PasswordResetTokenExpiredException();
        }

        if (!request.getNewPassword()
                .equals(request.getConfirmPassword())) {

            throw new PasswordMismatchException();
        }

        user.changePassword(
                passwordEncoder.encode(request.getNewPassword())
        );

        user.clearPasswordResetToken();

        userRepository.save(user);

        return new ResetPasswordResponse(
                "Password has been reset successfully."
        );
    }

}