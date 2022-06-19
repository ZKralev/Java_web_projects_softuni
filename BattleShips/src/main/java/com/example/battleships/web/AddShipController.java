package com.example.battleships.web;

import com.example.battleships.model.dtos.ShipDTO;
import com.example.battleships.model.dtos.UserRegistrationDTO;
import com.example.battleships.service.ShipService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AddShipController {

    private ShipService service;

    public AddShipController(ShipService service) {
        this.service = service;
    }


    @GetMapping("/ship-add")
    public String register(){
        return "ship-add.html";
    }

    @ModelAttribute("shipDTO")
    public ShipDTO initFormAddShip(){
        return new ShipDTO();
    }

    @PostMapping("/ship-add")
    public String addShip(@Valid ShipDTO shipDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        System.out.println(shipDTO.toString());

        if(bindingResult.hasErrors() || !this.service.addShip(shipDTO)){

            redirectAttributes.addFlashAttribute("shipDTO", shipDTO);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.shipDTO", bindingResult);

            return "redirect:/ship-add";
        }

        this.service.addShip(shipDTO);
        return "/home";
    }
    
}
