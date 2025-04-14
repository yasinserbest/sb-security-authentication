package com.maystorre.sb_security_authentication.service.impl;

import com.maystorre.sb_security_authentication.exceptions.APIException;
import com.maystorre.sb_security_authentication.mapper.UserMapper;
import com.maystorre.sb_security_authentication.model.dto.user.UserRequestDto;
import com.maystorre.sb_security_authentication.model.dto.user.UserResponseDto;
import com.maystorre.sb_security_authentication.model.entity.Role;
import com.maystorre.sb_security_authentication.model.entity.User;
import com.maystorre.sb_security_authentication.repository.RoleRepository;
import com.maystorre.sb_security_authentication.repository.UserRepository;
import com.maystorre.sb_security_authentication.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRespository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRespository, RoleRepository roleRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRespository = userRespository;
        this.roleRepository=roleRepository;
        this.userMapper=userMapper;
        this.passwordEncoder=passwordEncoder;

    }


    @Override
    public UserResponseDto createUser(UserRequestDto userDto) {

        Role role = roleRepository.findById(userDto.role())
                .orElseThrow(() ->  new APIException("Role not found", HttpStatus.NOT_FOUND));

        User user = userMapper.userRequestDtoToUser(userDto);

        String encodedPassword = passwordEncoder.encode(userDto.password());
        user.setPassword(encodedPassword);

        user.setRole(role);

        User savedUser = userRespository.save(user);
        return userMapper.userToUserResponseDto(savedUser);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRespository.findById(id).orElseThrow(() -> new APIException("User not found", HttpStatus.NOT_FOUND));
        return userMapper.userToUserResponseDto(user);

    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRespository.findAll();
        return userMapper.usersToUserResponseDtos(users);
    }

}
