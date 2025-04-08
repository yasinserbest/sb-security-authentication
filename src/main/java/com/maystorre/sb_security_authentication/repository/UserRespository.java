package com.maystorre.sb_security_authentication.repository;

import com.maystorre.sb_security_authentication.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User, Long> {
}
