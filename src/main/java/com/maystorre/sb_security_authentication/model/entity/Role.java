package com.maystorre.sb_security_authentication.model.entity;

import com.maystorre.sb_security_authentication.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, name = "role_name")
    private UserRole roleName;

    @JsonIgnore
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude // Avoid circular references in @ToString
    private List<User> users;
//
//    public Role(String roleName) {
//        this.roleName = roleName;
//    }
}
