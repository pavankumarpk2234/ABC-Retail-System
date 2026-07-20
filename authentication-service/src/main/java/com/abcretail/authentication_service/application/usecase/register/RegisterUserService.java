package com.abcretail.authentication_service.application.usecase.register;

import com.abcretail.authentication_service.application.dto.request.RegisterRequest;
import com.abcretail.authentication_service.application.dto.response.RegisterResponse;
import com.abcretail.authentication_service.application.mapper.RegisterUserMapper;
import com.abcretail.authentication_service.domain.exception.UserAlreadyExistsException;
import com.abcretail.authentication_service.domain.model.Role;
import com.abcretail.authentication_service.domain.model.User;
import com.abcretail.authentication_service.domain.model.UserStatus;
import com.abcretail.authentication_service.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepository userRepository;
    private final RegisterUserMapper registerUserMapper;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserService(UserRepository userRepository,
                               RegisterUserMapper registerUserMapper,
                               PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.registerUserMapper = registerUserMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {

        // Check email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists.");
        }

        // Check username already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists.");
        }

        // Convert request to domain model
        User user = registerUserMapper.toDomain(request);

        // Business rules
        user.setRole(Role.CUSTOMER);
        user.setStatus(UserStatus.ACTIVE);

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // Encode password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Save user
        User savedUser = userRepository.save(user);

        // Convert to response
        return registerUserMapper.toResponse(savedUser);
    }

}