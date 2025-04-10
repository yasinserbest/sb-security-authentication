package com.maystorre.sb_security_authentication.model.dto.user;

import com.maystorre.sb_security_authentication.enums.UserRole;


public record UserResponseDto(
        Long id,
        String username,
        String email,
        UserRole role
) {
}
