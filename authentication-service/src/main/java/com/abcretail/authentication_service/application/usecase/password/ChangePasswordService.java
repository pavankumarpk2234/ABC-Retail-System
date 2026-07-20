package com.abcretail.authentication_service.application.usecase.password;

import com.abcretail.authentication_service.application.dto.request.ChangePasswordRequest;
import com.abcretail.authentication_service.domain.exception.InvalidCredentialsException;
import com.abcretail.authentication_service.domain.exception.UserNotFoundException;
import com.abcretail.authentication_service.domain.model.User;
import com.abcretail.authentication_service.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordService implements ChangePasswordUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ChangePasswordService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Change the password of the authenticated user.
     *
     * @param userId  Authenticated user's ID
     * @param request Contains current password and new password
     */
    @Override
    public void changePassword(Long userId,
                               ChangePasswordRequest request) {

        // Find user
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with ID: " + userId
                        ));

        // Verify current password
        if (!passwordEncoder.matches(
                request.getCurrentPassword(),
                user.getPassword())) {

            throw new InvalidCredentialsException(
                    "Current password is incorrect."
            );
        }

        // Prevent using the same password again
        if (passwordEncoder.matches(
                request.getNewPassword(),
                user.getPassword())) {

            throw new InvalidCredentialsException(
                    "New password must be different from the current password."
            );
        }

        // Encode and update password
        user.setPassword(
                passwordEncoder.encode(request.getNewPassword())
        );

        // Save updated user
        userRepository.save(user);
    }
}