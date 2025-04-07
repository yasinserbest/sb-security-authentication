package com.maystorre.sb_security_authentication.model.entity;

import com.maystorre.sb_security_authentication.enums.UserRole;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private List<UserRole> roles;
}
