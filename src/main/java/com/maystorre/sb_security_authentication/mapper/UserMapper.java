package com.maystorre.sb_security_authentication.mapper;

import com.maystorre.sb_security_authentication.model.dto.user.UserRequestDto;
import com.maystorre.sb_security_authentication.model.dto.user.UserResponseDto;
import com.maystorre.sb_security_authentication.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", ignore = true) // We'll set it manually for source
    User userRequestDtoToUser(UserRequestDto dto);

    @Mapping(source = "role.roleName", target = "role") // Maps Role role to UserRole enum role in response
    @Mapping(source = "name", target = "username")
    UserResponseDto userToUserResponseDto(User user);

    List<UserResponseDto> usersToUserResponseDtos(List<User> users);

}
