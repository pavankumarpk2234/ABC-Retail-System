package com.abcretail.authentication_service.presentation.controller;

import com.abcretail.authentication_service.application.dto.request.ChangePasswordRequest;
import com.abcretail.authentication_service.application.dto.request.ForgotPasswordRequest;
import com.abcretail.authentication_service.application.dto.request.LoginRequest;
import com.abcretail.authentication_service.application.dto.request.RefreshTokenRequest;
import com.abcretail.authentication_service.application.dto.request.RegisterRequest;
import com.abcretail.authentication_service.application.dto.request.ResetPasswordRequest;
import com.abcretail.authentication_service.application.dto.response.ForgotPasswordResponse;
import com.abcretail.authentication_service.application.dto.response.LoginResponse;
import com.abcretail.authentication_service.application.dto.response.RegisterResponse;
import com.abcretail.authentication_service.application.dto.response.ResetPasswordResponse;
import com.abcretail.authentication_service.application.dto.response.UserResponse;
import com.abcretail.authentication_service.application.usecase.forgotpassword.ForgotPasswordUseCase;
import com.abcretail.authentication_service.application.usecase.login.LoginUserUseCase;
import com.abcretail.authentication_service.application.usecase.password.ChangePasswordUseCase;
import com.abcretail.authentication_service.application.usecase.profile.GetUserProfileUseCase;
import com.abcretail.authentication_service.application.usecase.refresh.RefreshTokenUseCase;
import com.abcretail.authentication_service.application.usecase.register.RegisterUserUseCase;
import com.abcretail.authentication_service.application.usecase.resetpassword.ResetPasswordUseCase;
import com.abcretail.authentication_service.infrastructure.security.userdetails.CustomUserDetails;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;
    private final GetUserProfileUseCase getUserProfileUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final ForgotPasswordUseCase forgotPasswordUseCase;
    private final ResetPasswordUseCase resetPasswordUseCase;

    public AuthenticationController(
            RegisterUserUseCase registerUserUseCase,
            LoginUserUseCase loginUserUseCase,
            GetUserProfileUseCase getUserProfileUseCase,
            ChangePasswordUseCase changePasswordUseCase,
            RefreshTokenUseCase refreshTokenUseCase,
            ForgotPasswordUseCase forgotPasswordUseCase,
            ResetPasswordUseCase resetPasswordUseCase) {

        this.registerUserUseCase = registerUserUseCase;
        this.loginUserUseCase = loginUserUseCase;
        this.getUserProfileUseCase = getUserProfileUseCase;
        this.changePasswordUseCase = changePasswordUseCase;
        this.refreshTokenUseCase = refreshTokenUseCase;
        this.forgotPasswordUseCase = forgotPasswordUseCase;
        this.resetPasswordUseCase = resetPasswordUseCase;
    }

    /**
     * Register a new user.
     *
     * POST http://localhost:8081/api/v1/auth/register
     */
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        RegisterResponse response = registerUserUseCase.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * Authenticate user and return JWT tokens.
     *
     * POST http://localhost:8081/api/v1/auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = loginUserUseCase.login(request);

        return ResponseEntity.ok(response);
    }

    /**
     * Generate a new Access Token using a valid Refresh Token.
     *
     * POST http://localhost:8081/api/v1/auth/refresh-token
     */
    @PostMapping("/refresh-token")
    public ResponseEntity<LoginResponse> refreshToken(
            @Valid @RequestBody RefreshTokenRequest request) {

        LoginResponse response =
                refreshTokenUseCase.refreshToken(request);

        return ResponseEntity.ok(response);
    }

    /**
     * Forgot Password
     *
     * POST http://localhost:8081/api/v1/auth/forgot-password
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<ForgotPasswordResponse> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequest request) {

        ForgotPasswordResponse response =
                forgotPasswordUseCase.forgotPassword(request);

        return ResponseEntity.ok(response);
    }

    /**
     * Reset Password
     *
     * POST http://localhost:8081/api/v1/auth/reset-password
     */
    @PostMapping("/reset-password")
    public ResponseEntity<ResetPasswordResponse> resetPassword(
            @Valid @RequestBody ResetPasswordRequest request) {

        ResetPasswordResponse response =
                resetPasswordUseCase.resetPassword(request);

        return ResponseEntity.ok(response);
    }

    /**
     * Get currently authenticated user's profile.
     *
     * GET http://localhost:8081/api/v1/auth/me
     */
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getProfile() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        UserResponse response =
                getUserProfileUseCase.getProfile(userDetails.getUserId());

        return ResponseEntity.ok(response);
    }

    /**
     * Change password of the authenticated user.
     *
     * PUT http://localhost:8081/api/v1/auth/change-password
     */
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @Valid @RequestBody ChangePasswordRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        changePasswordUseCase.changePassword(
                userDetails.getUserId(),
                request
        );

        return ResponseEntity.ok("Password changed successfully.");
    }

}