package com.abcretail.authentication_service.application.usecase.register;

import com.abcretail.authentication_service.application.dto.request.RegisterRequest;
import com.abcretail.authentication_service.application.dto.response.RegisterResponse;

public interface RegisterUserUseCase {

    /**
     * Registers a new user.
     *
     * @param request Registration request
     * @return Registration response
     */
    RegisterResponse register(RegisterRequest request);

}