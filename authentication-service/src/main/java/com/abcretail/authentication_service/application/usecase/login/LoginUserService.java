package com.abcretail.authentication_service.application.usecase.login;

import com.abcretail.authentication_service.application.dto.request.LoginRequest;
import com.abcretail.authentication_service.application.dto.response.LoginResponse;
import com.abcretail.authentication_service.application.mapper.LoginUserMapper;
import com.abcretail.authentication_service.domain.exception.InvalidCredentialsException;
import com.abcretail.authentication_service.domain.exception.UserNotFoundException;
import com.abcretail.authentication_service.domain.model.User;
import com.abcretail.authentication_service.domain.repository.UserRepository;
import com.abcretail.authentication_service.infrastructure.security.jwt.JwtProperties;
import com.abcretail.authentication_service.infrastructure.security.jwt.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginUserService implements LoginUserUseCase {

    private final UserRepository userRepository;
    private final LoginUserMapper loginUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    public LoginUserService(UserRepository userRepository,
                            LoginUserMapper loginUserMapper,
                            PasswordEncoder passwordEncoder,
                            JwtTokenProvider jwtTokenProvider,
                            JwtProperties jwtProperties) {
        this.userRepository = userRepository;
        this.loginUserMapper = loginUserMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtProperties = jwtProperties;
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user;

        // Login using Email or Username
        if (request.getLogin().contains("@")) {

            user = userRepository.findByEmail(request.getLogin())
                    .orElseThrow(() ->
                            new UserNotFoundException(
                                    "User not found with email: " + request.getLogin()));

        } else {

            user = userRepository.findByUsername(request.getLogin())
                    .orElseThrow(() ->
                            new UserNotFoundException(
                                    "User not found with username: " + request.getLogin()));
        }

        // Verify password
        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new InvalidCredentialsException(
                    "Invalid username/email or password."
            );
        }

        // Generate JWTs
        String accessToken = jwtTokenProvider.generateAccessToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);

        // Map User -> LoginResponse
        LoginResponse response = loginUserMapper.toResponse(user);

        // Populate token details
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        response.setTokenType("Bearer");
        response.setAccessTokenExpiresIn(
                jwtProperties.getAccessTokenExpiration()
        );
        response.setRefreshTokenExpiresIn(
                jwtProperties.getRefreshTokenExpiration()
        );

        return response;
    }

}