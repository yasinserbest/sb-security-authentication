package com.maystorre.sb_security_authentication.service;


import com.maystorre.sb_security_authentication.model.dto.user.UserResponseDto;

public interface AuthService {
    UserResponseDto getLoggedInUser();
}
