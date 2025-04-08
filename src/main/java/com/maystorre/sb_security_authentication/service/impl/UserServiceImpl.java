package com.maystorre.sb_security_authentication.service.impl;

import com.maystorre.sb_security_authentication.enums.UserRole;
import com.maystorre.sb_security_authentication.exceptions.APIException;
import com.maystorre.sb_security_authentication.model.dto.user.UserRequestDto;
import com.maystorre.sb_security_authentication.model.dto.user.UserResponseDto;
import com.maystorre.sb_security_authentication.model.entity.Role;
import com.maystorre.sb_security_authentication.model.entity.User;
import com.maystorre.sb_security_authentication.repository.RoleRepository;
import com.maystorre.sb_security_authentication.repository.UserRespository;
import com.maystorre.sb_security_authentication.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRespository userRespository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRespository userRespository, RoleRepository roleRepository) {
        this.userRespository = userRespository;
        this.roleRepository=roleRepository;
    }


    @Override
    public UserResponseDto createUser(UserRequestDto user) {
        // Find the role (or handle it if it doesn't exist)
        Long roleId = user.role() != null ? user.role() : 2L;

        Optional<Role> roleOptional = roleRepository.findById(roleId);
        Role role = roleOptional.orElseThrow(() -> new RuntimeException("Role not found: " + roleId));

        // Log the role information
        logger.info("Role found: {}", role);

        User savedUser = userRespository.save(userDtoToUserClass(user));
        return new UserResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getRole().getId());
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        return userRespository.findById(id)
                .map(u -> new UserResponseDto(u.getId(), u.getName(), u.getEmail(), u.getRole().getId()))
                .orElseThrow(() -> new APIException("User not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRespository.findAll().stream()
                .map(u -> new UserResponseDto(u.getId(), u.getName(), u.getEmail(), u.getRole().getId()))
                .toList();
    }

    private User userDtoToUserClass(UserRequestDto userRequestDto) {
        Long roleId = userRequestDto.role() != null ? userRequestDto.role() : 2L;
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        Role role = roleOptional.orElseThrow(() -> new RuntimeException("Role not found: " + roleId));

        return new User(userRequestDto.name(), userRequestDto.email(), userRequestDto.password(), role);
    }
}
