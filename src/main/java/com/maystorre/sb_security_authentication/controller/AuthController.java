package com.maystorre.sb_security_authentication.controller;

import com.maystorre.sb_security_authentication.model.dto.auth.SignInResponse;
import com.maystorre.sb_security_authentication.model.dto.message.MessageDto;
import com.maystorre.sb_security_authentication.model.dto.user.UserRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
        SignInResponse response = authService.signIn(signInRequest);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,
                response.jwtToken())
                .body(response);
    }

    @PostMapping("/signout")
    public ResponseEntity<MessageDto> signOut() {
        return ResponseEntity.ok().body(new MessageDto("You've been successfully signed out!"));

    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@Valid @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(authService.signUp(userRequestDto));
    }
}
