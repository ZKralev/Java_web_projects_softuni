package bg.softuni.HappyCats.web;

import bg.softuni.HappyCats.model.DTOS.AddBookingDTO;
import bg.softuni.HappyCats.service.BookingService;
import bg.softuni.HappyCats.service.HappyPetsUserDetailsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/booking")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/")
    public String addBooking(Model model) {
        if (!model.containsAttribute("addBookingDTO")) {
            model.addAttribute("addBookingDTO", new AddBookingDTO());
        }
        return "booking";
    }

    @PostMapping("/")
    public String addOffer(@Valid AddBookingDTO addBookingDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @AuthenticationPrincipal HappyPetsUserDetailsService userDetails) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addBookingDTO", addBookingDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addBookingDTO",
                    bindingResult);
            return "redirect:/booking";
        }

        bookingService.addBooking(addBookingDTO);

        return "redirect:/booking";
    }

}
