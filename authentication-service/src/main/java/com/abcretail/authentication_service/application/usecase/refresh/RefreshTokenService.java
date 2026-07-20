package com.abcretail.authentication_service.application.usecase.refresh;

import com.abcretail.authentication_service.application.dto.request.RefreshTokenRequest;
import com.abcretail.authentication_service.application.dto.response.LoginResponse;
import com.abcretail.authentication_service.domain.exception.InvalidCredentialsException;
import com.abcretail.authentication_service.domain.exception.UserNotFoundException;
import com.abcretail.authentication_service.domain.model.User;
import com.abcretail.authentication_service.domain.repository.UserRepository;
import com.abcretail.authentication_service.infrastructure.security.jwt.JwtProperties;
import com.abcretail.authentication_service.infrastructure.security.jwt.JwtTokenProvider;
import com.abcretail.authentication_service.infrastructure.security.jwt.JwtTokenValidator;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService implements RefreshTokenUseCase {

    private final JwtTokenValidator jwtTokenValidator;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final JwtProperties jwtProperties;

    public RefreshTokenService(JwtTokenValidator jwtTokenValidator,
                               JwtTokenProvider jwtTokenProvider,
                               UserRepository userRepository,
                               JwtProperties jwtProperties) {
        this.jwtTokenValidator = jwtTokenValidator;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.jwtProperties = jwtProperties;
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest request) {

        // Validate refresh token
        if (!jwtTokenValidator.validateRefreshToken(request.getRefreshToken())) {
            throw new InvalidCredentialsException("Invalid or expired refresh token.");
        }

        // Extract user ID
        Long userId = jwtTokenValidator.getUserId(request.getRefreshToken());

        // Load user
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with ID: " + userId
                        ));

        // Generate ONLY a new access token
        String accessToken = jwtTokenProvider.generateAccessToken(user);

        // Build response
        LoginResponse response = new LoginResponse();
        response.setUserId(user.getId());
        response.setAccessToken(accessToken);

        // Reuse the same refresh token
        response.setRefreshToken(request.getRefreshToken());

        response.setTokenType("Bearer");
        response.setAccessTokenExpiresIn(jwtProperties.getAccessTokenExpiration());

        // Same refresh token still has the original expiration
        response.setRefreshTokenExpiresIn(jwtProperties.getRefreshTokenExpiration());

        return response;
    }
}