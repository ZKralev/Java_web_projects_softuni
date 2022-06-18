package com.example.battleships.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddShipController {

    @GetMapping("/addShip")
    public String register(){
        return "ship-add.html";
    }
    
}
