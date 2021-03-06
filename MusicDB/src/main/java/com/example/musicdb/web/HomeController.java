package com.example.musicdb.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String loggedOutIndex(){
        return "index.html";
    }

    @GetMapping("/home")
    public String loggedInIndex(){
        return "home.html";
    }
}
