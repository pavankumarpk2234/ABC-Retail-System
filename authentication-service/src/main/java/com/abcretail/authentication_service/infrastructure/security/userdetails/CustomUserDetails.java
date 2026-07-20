package com.abcretail.authentication_service.infrastructure.security.userdetails;

import com.abcretail.authentication_service.domain.model.User;
import com.abcretail.authentication_service.domain.model.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    /**
     * Returns the wrapped domain user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Returns the authenticated user's ID.
     * Used throughout the application after JWT authentication.
     */
    public Long getUserId() {
        return user.getId();
    }

    /**
     * Returns the authenticated user's email.
     */
    public String getEmail() {
        return user.getEmail();
    }

    /**
     * Returns the authenticated user's display username.
     */
    public String getDisplayUsername() {
        return user.getUsername();
    }

    /**
     * Returns the user's role.
     */
    public String getRole() {
        return user.getRole().name();
    }

    /**
     * Returns the user's authorities (roles).
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
        );
    }

    /**
     * Returns the hashed password.
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Required by Spring Security.
     * Returns a unique identifier for the authenticated user.
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /**
     * Indicates whether the account has expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the account is locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return user.getStatus() != UserStatus.LOCKED;
    }

    /**
     * Indicates whether the credentials have expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the account is enabled.
     */
    @Override
    public boolean isEnabled() {
        return user.getStatus() == UserStatus.ACTIVE;
    }

}