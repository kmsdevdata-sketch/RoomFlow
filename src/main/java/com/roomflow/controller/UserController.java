package com.roomflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user/me")
    public String myInfo() {
        return "user/profile";
    }
}
