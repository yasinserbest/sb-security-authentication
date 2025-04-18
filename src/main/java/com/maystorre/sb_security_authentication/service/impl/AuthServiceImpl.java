package com.maystorre.sb_security_authentication.service.impl;

import com.maystorre.sb_security_authentication.enums.UserRole;
import com.maystorre.sb_security_authentication.model.dto.user.UserRequestDto;
import com.maystorre.sb_security_authentication.model.entity.Role;
import com.maystorre.sb_security_authentication.model.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.maystorre.sb_security_authentication.model.dto.auth.SignInRequest;
import com.maystorre.sb_security_authentication.model.dto.user.UserResponseDto;
import com.maystorre.sb_security_authentication.security.services.UserDetailsImpl;
import com.maystorre.sb_security_authentication.service.AuthService;
import com.maystorre.sb_security_authentication.service.UserService;

@Service
public class AuthServiceImpl implements AuthService {

    private UserService userService;
    private AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public UserResponseDto getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        if (authentication == null || !authentication.isAuthenticated()) { // // bu kısmı not olsun diye silmedim bilerek yorum satırına aldım. bu if check ççalışmaz çünkü bu endpoint'in senin .authneticated() böyle olunca bu katmana inmez bile, direk CustomAuthProvider'da auhenticate'den geçemez ve AuthEntryPoint'ten hatayı fırlatır.
//            throw new RuntimeException("No authenticated user found");
//        }

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userService.getUserById(userDetails.getId());
    }


    @Override
    public  UserResponseDto signIn(SignInRequest signInRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                signInRequestDto.getEmail(),
                signInRequestDto.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            return new UserResponseDto(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                UserRole.valueOf(userDetails.getAuthorities().iterator().next().getAuthority())
            );
    }

    @Override
    public UserResponseDto signUp(UserRequestDto user) {
        return userService.createUser(user);
    }

}
