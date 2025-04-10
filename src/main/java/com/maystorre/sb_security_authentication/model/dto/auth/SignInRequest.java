package com.maystorre.sb_security_authentication.model.dto.auth;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
}
