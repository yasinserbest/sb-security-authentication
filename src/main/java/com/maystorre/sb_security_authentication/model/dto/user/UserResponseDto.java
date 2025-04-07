package com.maystorre.sb_security_authentication.model.dto.user;

import com.maystorre.sb_security_authentication.enums.UserRole;

import java.util.List;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        List<UserRole> role
) {
}
