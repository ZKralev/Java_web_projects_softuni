package com.example.battleships.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorizationController {
    

    @GetMapping("/register")
    public String register(){
        return "register.html";
    }



    @GetMapping("/login")
    public String login(){
        return "login.html";
    }
}
