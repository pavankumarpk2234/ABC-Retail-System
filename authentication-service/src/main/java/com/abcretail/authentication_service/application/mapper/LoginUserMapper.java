package com.abcretail.authentication_service.application.mapper;

import com.abcretail.authentication_service.application.dto.response.LoginResponse;
import com.abcretail.authentication_service.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoginUserMapper {

    /**
     * Converts Domain User to LoginResponse.
     *
     * JWT-related fields are populated in LoginUserService.
     */
    @Mapping(source = "id", target = "userId")
    @Mapping(target = "accessToken", ignore = true)
    @Mapping(target = "refreshToken", ignore = true)
    @Mapping(target = "tokenType", ignore = true)
    @Mapping(target = "accessTokenExpiresIn", ignore = true)
    @Mapping(target = "refreshTokenExpiresIn", ignore = true)
    LoginResponse toResponse(User user);

}