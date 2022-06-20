package com.example.battleships.web;

import com.example.battleships.model.Ships;
import com.example.battleships.repositories.ShipRepository;
import com.example.battleships.user.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {


    @GetMapping("/")
    public String loggedOutIndex(){
        return "index";
    }

    @GetMapping("/home")
    public String loggedInIndex(){
        return "home";
    }



}
