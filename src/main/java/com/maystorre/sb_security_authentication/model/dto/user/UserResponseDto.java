package com.maystorre.sb_security_authentication.model.dto.user;

import com.maystorre.sb_security_authentication.enums.UserRole;
import com.maystorre.sb_security_authentication.model.entity.Role;

import java.util.List;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        Long role
) {
}
