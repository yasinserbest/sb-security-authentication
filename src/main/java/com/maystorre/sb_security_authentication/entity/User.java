package com.maystorre.sb_security_authentication.entity;

import com.maystorre.sb_security_authentication.enums.UserRole;

import java.util.List;

public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private List<UserRole> roles;
}
