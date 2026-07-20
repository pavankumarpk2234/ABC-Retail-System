package com.abcretail.authentication_service.application.usecase.password;

import com.abcretail.authentication_service.application.dto.request.ChangePasswordRequest;

public interface ChangePasswordUseCase {

    /**
     * Changes the password of the authenticated user.
     *
     * @param userId  Authenticated user's ID
     * @param request Contains current password and new password
     */
    void changePassword(Long userId, ChangePasswordRequest request);

}