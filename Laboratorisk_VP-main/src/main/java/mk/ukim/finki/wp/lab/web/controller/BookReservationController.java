package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

@Controller
@RequestMapping("/bookReservation")
public class BookReservationController {
    private final BookReservationService bookReservationService;

    public BookReservationController(BookReservationService bookReservationService) {
        this.bookReservationService = bookReservationService;
    }

    @PostMapping()
    public String saveBook(@RequestParam String bookTitle,
                           @RequestParam String readerName,
                           @RequestParam String readerAddress,
                           @RequestParam int numCopies,
                           HttpServletRequest request,
                           Model model){

        String clientIp = request.getRemoteAddr();
        BookReservation reservation = bookReservationService.placeReservation(bookTitle, readerName, readerAddress, numCopies);
        model.addAttribute("reservation", reservation);
        model.addAttribute("clientIp", clientIp);
        return "reservationConfirmation";
    }
}
