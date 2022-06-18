package com.example.battleships.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.battleships.model.dtos.UserRegistrationDTO;
import com.example.battleships.service.AuthorizationService;

@Controller
public class AuthorizationController {
    

    private AuthorizationService authService;

    @Autowired
    public AuthorizationController(AuthorizationService authorizationService) {
        this.authService = authorizationService;
    }

    @ModelAttribute("userRegistrationDTO")
    public UserRegistrationDTO  initForm(Model model){
            return new UserRegistrationDTO();
    }

    @GetMapping("/register")
    public String register(){
        return "register.html";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        System.out.println(userRegistrationDTO.toString());

        if(bindingResult.hasErrors()){

            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO");
            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);

            return "redirect:/home/register";
        }
        this.authService.register(userRegistrationDTO);
        return "/login";
    }


    @GetMapping("/login")
    public String login(){
        return "login.html";
    }
}
