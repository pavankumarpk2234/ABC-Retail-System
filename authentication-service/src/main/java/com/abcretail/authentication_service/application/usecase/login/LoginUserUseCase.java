package com.abcretail.authentication_service.application.usecase.login;

import com.abcretail.authentication_service.application.dto.request.LoginRequest;
import com.abcretail.authentication_service.application.dto.response.LoginResponse;

public interface LoginUserUseCase {

    /**
     * Authenticates a user using email and password.
     *
     * @param request Login request
     * @return Login response
     */
    LoginResponse login(LoginRequest request);

}