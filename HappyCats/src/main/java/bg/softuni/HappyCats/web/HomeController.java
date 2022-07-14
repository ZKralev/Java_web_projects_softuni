package bg.softuni.HappyCats.web;

import bg.softuni.HappyCats.model.entity.Route;
import bg.softuni.HappyCats.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final RouteService routeService;

    @Autowired
    public HomeController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/service")
    public String service(){
        return "service";
    }

    @GetMapping("/price")
    public String price(){
        return "price";
    }

    @GetMapping("/booking")
    public String booking(){
        return "booking";
    }

}
