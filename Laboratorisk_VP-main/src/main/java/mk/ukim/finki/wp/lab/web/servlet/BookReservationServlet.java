package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "BookReservationServlet", urlPatterns = "/bookReservation")
public class BookReservationServlet extends HttpServlet {
    private final BookReservationService bookReservationService;
    private final SpringTemplateEngine springTemplateEngine;

    public BookReservationServlet(BookReservationService bookReservationService, SpringTemplateEngine springTemplateEngine) {
        this.bookReservationService = bookReservationService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);
        String bookTitle = req.getParameter("bookTitle");
        String readerName = req.getParameter("readerName");
        String readerAddress = req.getParameter("readerAddress");
        String numCopiesStr = req.getParameter("numCopies");
        int numCopies = Integer.parseInt(numCopiesStr);


        String clientIp = req.getRemoteAddr();
        BookReservation reservation = bookReservationService.placeReservation(bookTitle, readerName, readerAddress, numCopies);
        context.setVariable("reservation", reservation);
        context.setVariable("clientIp", clientIp);
        try {
            // This is where the diagnostic block goes
            springTemplateEngine.process("reservationConfirmation", context, resp.getWriter());
        } catch (Exception e) {
            System.err.println("Thymeleaf error type: " + e.getClass().getName());
            System.err.println("Thymeleaf error msg : " + e.getMessage());
            Throwable cause = e.getCause();
            while (cause != null) {
                System.err.println("  caused by: " + cause.getClass().getName() + " - " + cause.getMessage());
                cause = cause.getCause();
            }
            throw e; // still rethrow so the app shows 500
        }
    }
}
