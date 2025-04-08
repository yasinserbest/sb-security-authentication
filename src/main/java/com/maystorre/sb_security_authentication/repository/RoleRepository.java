package com.maystorre.sb_security_authentication.repository;

import com.maystorre.sb_security_authentication.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(String name); // For finding a role by its name
}