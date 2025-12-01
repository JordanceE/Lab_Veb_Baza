package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookListServlet", urlPatterns = "/books")
public class BookListServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final BookService bookService;

    public BookListServlet(SpringTemplateEngine springTemplateEngine, BookService bookService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);
        String text = req.getParameter("text");
        String ratingStr = req.getParameter("rating");
        Double rating = null;
        if (ratingStr != null && !ratingStr.isEmpty()){
            try { rating = Double.parseDouble(ratingStr); } catch (NumberFormatException ignored) {}
        }
        List<Book> books = (text != null || rating != null)
                ? bookService.searchBooks(text, rating)
                : bookService.listAll();
        context.setVariable("books", books);
        context.setVariable("text", text == null ? "" : text);
        context.setVariable("rating", ratingStr == null ? "" : ratingStr);
        springTemplateEngine.process("listBooks", context, resp.getWriter());
    }
}
