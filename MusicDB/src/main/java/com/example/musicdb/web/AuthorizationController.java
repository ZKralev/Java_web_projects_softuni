package com.example.musicdb.web;

import com.example.musicdb.model.dtos.UserLoginDTO;
import com.example.musicdb.model.dtos.UserRegistrationDTO;
import com.example.musicdb.services.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthorizationController {

    private final AuthorizationService authService;

    @Autowired
    public AuthorizationController(AuthorizationService authorization) {
        this.authService = authorization;
    }

    @ModelAttribute("userRegistrationDTO")
    public UserRegistrationDTO initFormReg(){
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

            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.userRegistrationDTO", bindingResult);


            return "redirect:/register";
        }
        this.authService.register(userRegistrationDTO);
        return "/login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        authService.logout();
        return "redirect:/";
    }

    @ModelAttribute("userLoginDTO")
    public UserLoginDTO initFormLog(){
        return new UserLoginDTO();
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDTO userLoginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.authService.login(userLoginDTO)) {
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO",
                    bindingResult);
            bindingResult.rejectValue("password", "InvalidPasswordError", "Invalid password.");
            return "redirect:/login";
        }

        if(!this.authService.login(userLoginDTO)){
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("badCredentials", true);

            return "redirect:/login";
        }

        return "redirect:/home";
    }

}
