package com.maystorre.sb_security_authentication.service.impl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.maystorre.sb_security_authentication.model.dto.user.UserResponseDto;
import com.maystorre.sb_security_authentication.security.services.UserDetailsImpl;
import com.maystorre.sb_security_authentication.service.AuthService;
import com.maystorre.sb_security_authentication.service.UserService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    Authentication authentication;

    public AuthServiceImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
        public UserResponseDto getLoggedInUser() {
        // Get authentication from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No authenticated user found");
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userService.getUserById(userDetails.getId());
    }

}
