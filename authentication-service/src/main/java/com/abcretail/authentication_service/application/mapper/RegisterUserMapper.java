package com.abcretail.authentication_service.application.mapper;

import com.abcretail.authentication_service.application.dto.request.RegisterRequest;
import com.abcretail.authentication_service.application.dto.response.RegisterResponse;
import com.abcretail.authentication_service.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegisterUserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toDomain(RegisterRequest request);

    @Mapping(source = "createdAt", target = "registeredAt")
    RegisterResponse toResponse(User user);

}