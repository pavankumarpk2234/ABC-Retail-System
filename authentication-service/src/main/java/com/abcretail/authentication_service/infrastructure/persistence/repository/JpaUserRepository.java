package com.abcretail.authentication_service.infrastructure.persistence.repository;

import com.abcretail.authentication_service.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Find a user by email.
     *
     * @param email User email
     * @return Optional UserEntity
     */
    Optional<UserEntity> findByEmail(String email);

    /**
     * Find a user by username.
     *
     * @param username Username
     * @return Optional UserEntity
     */
    Optional<UserEntity> findByUsername(String username);

    /**
     * Check whether a user exists with the given email.
     *
     * @param email User email
     * @return true if exists
     */
    boolean existsByEmail(String email);

    /**
     * Check whether a user exists with the given username.
     *
     * @param username Username
     * @return true if exists
     */
    boolean existsByUsername(String username);

}