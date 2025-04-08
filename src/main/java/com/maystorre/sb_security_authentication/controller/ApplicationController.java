package com.maystorre.sb_security_authentication.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    @RequestMapping("/user")
    public String getUser() {
        return "Hello User!";
    }

    public String getAdmin() {
        return "Hello Admin!";
    }
}
