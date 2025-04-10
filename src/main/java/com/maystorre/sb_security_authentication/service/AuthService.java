package com.maystorre.sb_security_authentication.service;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;

import com.maystorre.sb_security_authentication.model.dto.auth.SignInRequest;
import com.maystorre.sb_security_authentication.model.dto.user.UserResponseDto;

public interface AuthService {
    UserResponseDto getLoggedInUser();
    UserResponseDto signIn(SignInRequest signInRequest);

}
