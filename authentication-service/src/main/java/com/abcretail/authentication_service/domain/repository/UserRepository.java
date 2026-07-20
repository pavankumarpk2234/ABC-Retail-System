package com.abcretail.authentication_service.domain.repository;

import com.abcretail.authentication_service.domain.model.User;

import java.util.Optional;

public interface UserRepository {

    /**
     * Save a new user or update an existing user.
     * Used to Register User
     * @param user Domain user object
     * @return Saved user
     */
    User save(User user);

    /**
     * Find a user by ID.
     * Used to Get Profile
     * @param id User ID
     * @return Optional user
     */
    Optional<User> findById(Long id);

    /**
     * Find a user by email.
     * Used to Login User
     * @param email User email
     * @return Optional user
     */
    Optional<User> findByEmail(String email);

    /**
     * Find a user by username.
     * Used to Login User
     * @param username Username
     * @return Optional user
     */
    Optional<User> findByUsername(String username);

    /**
     * Check whether a user already exists with the given email.
     * Used by Register User
     * @param email User email
     * @return true if exists
     */
    boolean existsByEmail(String email);

    /**
     * Check whether a user already exists with the given username.
     * Used to Register User
     * @param username Username
     * @return true if exists
     */
    boolean existsByUsername(String username);

    /**
     * Delete a user by ID.
     * (Can later be implemented as soft delete.)
     * Used to Future Features
     * @param id User ID
     */
    void deleteById(Long id);

}