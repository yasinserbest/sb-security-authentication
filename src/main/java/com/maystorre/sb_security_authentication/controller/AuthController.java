package com.maystorre.sb_security_authentication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maystorre.sb_security_authentication.model.dto.user.UserResponseDto;
import com.maystorre.sb_security_authentication.service.AuthService;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping("/getLoggedInUser")
    public ResponseEntity<UserResponseDto> getLoggedInUser() {
        return ResponseEntity.ok(authService.getLoggedInUser());
    }
}
