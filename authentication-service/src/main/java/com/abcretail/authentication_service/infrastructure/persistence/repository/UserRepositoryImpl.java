package com.abcretail.authentication_service.infrastructure.persistence.repository;

import com.abcretail.authentication_service.domain.model.User;
import com.abcretail.authentication_service.domain.repository.UserRepository;
import com.abcretail.authentication_service.infrastructure.persistence.entity.UserEntity;
import com.abcretail.authentication_service.infrastructure.persistence.mapper.UserEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final UserEntityMapper userEntityMapper;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository,
                              UserEntityMapper userEntityMapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.userEntityMapper = userEntityMapper;
    }

    /**
     * Save or update a user.
     */
    @Override
    public User save(User user) {

        UserEntity userEntity = userEntityMapper.toEntity(user);

        UserEntity savedUserEntity = jpaUserRepository.save(userEntity);

        return userEntityMapper.toDomain(savedUserEntity);
    }

    /**
     * Find user by ID.
     */
    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id)
                .map(userEntityMapper::toDomain);
    }

    /**
     * Find user by email.
     */
    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email)
                .map(userEntityMapper::toDomain);
    }

    /**
     * Find user by username.
     */
    @Override
    public Optional<User> findByUsername(String username) {
        return jpaUserRepository.findByUsername(username)
                .map(userEntityMapper::toDomain);
    }

    /**
     * Find user by password reset token.
     */
    @Override
    public Optional<User> findByPasswordResetToken(String token) {
        return jpaUserRepository.findByPasswordResetToken(token)
                .map(userEntityMapper::toDomain);
    }

    /**
     * Check whether email already exists.
     */
    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    /**
     * Check whether username already exists.
     */
    @Override
    public boolean existsByUsername(String username) {
        return jpaUserRepository.existsByUsername(username);
    }

    /**
     * Delete user by ID.
     */
    @Override
    public void deleteById(Long id) {
        jpaUserRepository.deleteById(id);
    }

}