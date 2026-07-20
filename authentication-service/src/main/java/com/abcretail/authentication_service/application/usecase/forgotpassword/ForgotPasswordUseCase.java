package com.abcretail.authentication_service.application.usecase.forgotpassword;

import com.abcretail.authentication_service.application.dto.request.ForgotPasswordRequest;
import com.abcretail.authentication_service.application.dto.response.ForgotPasswordResponse;

public interface ForgotPasswordUseCase {

    /**
     * Generates a password reset token and sends
     * password reset instructions to the user's email.
     *
     * For security reasons, this method always returns
     * the same response whether the email exists or not.
     *
     * @param request Forgot password request
     * @return Generic success response
     */
    ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request);

}