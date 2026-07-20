package com.abcretail.authentication_service.application.usecase.profile;

import com.abcretail.authentication_service.application.dto.response.UserResponse;
import com.abcretail.authentication_service.application.mapper.GetUserProfileMapper;
import com.abcretail.authentication_service.domain.exception.UserNotFoundException;
import com.abcretail.authentication_service.domain.model.User;
import com.abcretail.authentication_service.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class GetUserProfileService implements GetUserProfileUseCase {

    private final UserRepository userRepository;
    private final GetUserProfileMapper getUserProfileMapper;

    public GetUserProfileService(UserRepository userRepository,
                                 GetUserProfileMapper getUserProfileMapper) {
        this.userRepository = userRepository;
        this.getUserProfileMapper = getUserProfileMapper;
    }

    /**
     * Get the profile of the authenticated user.
     *
     * @param userId Authenticated user's ID
     * @return User profile
     */
    @Override
    public UserResponse getProfile(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found with ID: " + userId
                        ));

        return getUserProfileMapper.toResponse(user);
    }
}