package com.maystorre.sb_security_authentication.service.impl;

import com.maystorre.sb_security_authentication.enums.UserRole;
import com.maystorre.sb_security_authentication.exceptions.APIException;
import com.maystorre.sb_security_authentication.model.dto.user.UserRequestDto;
import com.maystorre.sb_security_authentication.model.dto.user.UserResponseDto;
import com.maystorre.sb_security_authentication.model.entity.User;
import com.maystorre.sb_security_authentication.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private List<User> users = new ArrayList<>();
    private Long id = 0L;

    @Override
    public UserResponseDto createUser(UserRequestDto user) {
        id++;
        users.add(userDtoToUserClass(user));
        return new UserResponseDto(id, user.name(), user.email(), List.of(UserRole.ROLE_USER));
    }

    @Override
    public UserResponseDto getUserById(Long id) {
         for(User u : users) {
             if(u.getId() == id) {
                 return new UserResponseDto(u.getId(), u.getName(), u.getEmail(), u.getRoles());
             }
         }
         throw new APIException("User not found", HttpStatus.NOT_FOUND);

    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return users.stream()
                .map(user -> new UserResponseDto(user.getId(), user.getName(), user.getEmail(),user.getRoles()))
                .toList();
    }

    private User userDtoToUserClass(UserRequestDto userRequestDto) {
        return new User(id, userRequestDto.name(), userRequestDto.email(), userRequestDto.password(), List.of(UserRole.ROLE_USER));
    }
}
