package com.abcretail.authentication_service.infrastructure.persistence.mapper;

import com.abcretail.authentication_service.domain.model.User;
import com.abcretail.authentication_service.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    /**
     * Converts Domain User to Persistence Entity.
     *
     * @param user Domain model
     * @return JPA entity
     */
    UserEntity toEntity(User user);

    /**
     * Converts Persistence Entity to Domain User.
     *
     * @param userEntity JPA entity
     * @return Domain model
     */
    User toDomain(UserEntity userEntity);

}