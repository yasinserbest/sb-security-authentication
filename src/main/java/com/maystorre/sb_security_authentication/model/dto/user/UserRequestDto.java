package com.maystorre.sb_security_authentication.model.dto.user;

import com.maystorre.sb_security_authentication.enums.UserRole;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UserRequestDto(

        @NotNull
        String name,

        @NotNull
        String email,

        @NotNull
        String password

) {


}
