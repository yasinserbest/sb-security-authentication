package com.maystorre.sb_security_authentication.model.dto.auth;

import com.maystorre.sb_security_authentication.enums.UserRole;

public record SignInResponse(
        Long id,
        String username,
        String email,
        UserRole role,
        String jwtToken) {
}
