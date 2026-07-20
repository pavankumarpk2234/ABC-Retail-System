package com.abcretail.authentication_service.infrastructure.security.userdetails;

import com.abcretail.authentication_service.domain.model.User;
import com.abcretail.authentication_service.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Spring Security requires this method.
     *
     * Supports login using either:
     * - Email
     * - Username
     */
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(usernameOrEmail)
                .or(() -> userRepository.findByUsername(usernameOrEmail))
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found: " + usernameOrEmail
                        ));

        return new CustomUserDetails(user);
    }

    /**
     * Loads a user by ID.
     *
     * Used by JwtAuthenticationFilter after extracting
     * the user ID from the JWT token.
     */
    public UserDetails loadUserById(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found with ID: " + userId
                        ));

        return new CustomUserDetails(user);
    }
}