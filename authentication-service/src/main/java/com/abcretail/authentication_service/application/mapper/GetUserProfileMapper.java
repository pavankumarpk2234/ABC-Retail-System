package com.abcretail.authentication_service.application.mapper;

import com.abcretail.authentication_service.application.dto.response.UserResponse;
import com.abcretail.authentication_service.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetUserProfileMapper {

    /**
     * Converts Domain User to UserResponse.
     *
     * @param user Domain model
     * @return User response
     */
    UserResponse toResponse(User user);

}