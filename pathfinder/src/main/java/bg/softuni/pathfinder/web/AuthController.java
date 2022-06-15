package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.DTOS.UserRegistrationDTO;
import bg.softuni.pathfinder.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {

    public AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ModelAttribute("userRegistrationDTO")
    public UserRegistrationDTO  initForm(Model model){
            return new UserRegistrationDTO();
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        System.out.println(userRegistrationDTO.toString());

        if(bindingResult.hasErrors()){

            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO");
            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);

            return "redirect:/register";
        }
        this.authService.register(userRegistrationDTO);
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
