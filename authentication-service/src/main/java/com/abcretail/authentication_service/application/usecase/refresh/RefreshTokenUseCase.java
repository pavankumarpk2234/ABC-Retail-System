package com.abcretail.authentication_service.application.usecase.refresh;

import com.abcretail.authentication_service.application.dto.request.RefreshTokenRequest;
import com.abcretail.authentication_service.application.dto.response.LoginResponse;

public interface RefreshTokenUseCase {

    /**
     * Generates a new access token and refresh token
     * using a valid refresh token.
     *
     * @param request Refresh token request
     * @return New authentication tokens
     */
    LoginResponse refreshToken(RefreshTokenRequest request);

}