package com.maystorre.sb_security_authentication.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maystorre.sb_security_authentication.model.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@NoArgsConstructor
@Data
public class UserDetailsImpl implements UserDetails { //UserDetails'i implemente edip customlaştırdık
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String email; //örneğin email yokken email ekledik

    @JsonIgnore
    private String password;

    private GrantedAuthority authority;

    public UserDetailsImpl(Long id, String name, String email, String password,
                           GrantedAuthority authority) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.authority = authority;
    }

    public static UserDetailsImpl build(User user) { //constr. function

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRoleName().name());


        return new UserDetailsImpl(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                authority
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(authority); // userDetail interface of sb return colection but our user can have only one authority instead of a collection.
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

}