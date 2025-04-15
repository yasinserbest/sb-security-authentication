package com.maystorre.sb_security_authentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
public class ApplicationController {


    @GetMapping("/user")
    public String privateUser() {
        return "Hello, authenticated user!";
    }

    @GetMapping("/admin")
    public String privateAdmin() {
        return "Welcome, admin!";
    }
}
