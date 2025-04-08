package com.maystorre.sb_security_authentication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SqlRunner implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public SqlRunner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        // Example: insert data
        jdbcTemplate.update("INSERT INTO roles (role_name) VALUES (?)",  "ROLE_USER");
        jdbcTemplate.update("INSERT INTO roles (role_name) VALUES (?)",  "ROLE_ADMIN");
        jdbcTemplate.update(
    "INSERT INTO users (email, name, password, role_id) VALUES (?, ?, ?, ?)",
    "a@mail.com", "ahmet", "test123", 1
);
        jdbcTemplate.update("INSERT INTO users (email,name,password,role_id) VALUES (?,?,?,?)",  "b@mail.com","mehmet","test123",2);


        System.out.println(" data inserted.");
    }
}
