package com.maystorre.sb_security_authentication.controller;

import com.maystorre.sb_security_authentication.model.dto.user.UserRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maystorre.sb_security_authentication.model.dto.auth.SignInRequest;
import com.maystorre.sb_security_authentication.model.dto.user.UserResponseDto;
import com.maystorre.sb_security_authentication.service.AuthService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping("/getLoggedInUser")
    public ResponseEntity<UserResponseDto> getLoggedInUser() {
        return ResponseEntity.ok(authService.getLoggedInUser());
    }


    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }

    @PostMapping("/signout")
    public ResponseEntity<String> signOut() {
        // Clear the authentication
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Successfully signed out");
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@Valid @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(authService.signUp(userRequestDto));
    }
}
