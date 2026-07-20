package com.abcretail.authentication_service.application.usecase.resetpassword;

import com.abcretail.authentication_service.application.dto.request.ResetPasswordRequest;
import com.abcretail.authentication_service.application.dto.response.ResetPasswordResponse;

public interface ResetPasswordUseCase {

    /**
     * Reset a user's password using a valid password reset token.
     *
     * @param request Reset password request
     * @return Reset password response
     */
    ResetPasswordResponse resetPassword(ResetPasswordRequest request);

}