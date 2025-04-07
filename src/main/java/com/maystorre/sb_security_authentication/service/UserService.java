package com.maystorre.sb_security_authentication.service;

import com.maystorre.sb_security_authentication.model.dto.user.UserRequestDto;
import com.maystorre.sb_security_authentication.model.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto user);
    UserResponseDto getUserById(Long id) throws RuntimeException ;
    List<UserResponseDto> getAllUsers();
}
