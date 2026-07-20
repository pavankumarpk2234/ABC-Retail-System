package com.abcretail.authentication_service.application.usecase.profile;

import com.abcretail.authentication_service.application.dto.response.UserResponse;

public interface GetUserProfileUseCase {

    /**
     * Get the profile of the authenticated user.
     *
     * @param userId Authenticated user's ID extracted from JWT
     * @return User profile
     */
    UserResponse getProfile(Long userId);

}