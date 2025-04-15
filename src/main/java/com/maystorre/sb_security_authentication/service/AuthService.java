package com.maystorre.sb_security_authentication.service;


import com.maystorre.sb_security_authentication.model.dto.auth.SignInResponse;
import com.maystorre.sb_security_authentication.model.dto.user.UserRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;

import com.maystorre.sb_security_authentication.model.dto.auth.SignInRequest;
import com.maystorre.sb_security_authentication.model.dto.user.UserResponseDto;

public interface AuthService {
    UserResponseDto getLoggedInUser();
    SignInResponse signIn(SignInRequest signInRequest);
    UserResponseDto signUp(UserRequestDto user);
    String signOut();

}
