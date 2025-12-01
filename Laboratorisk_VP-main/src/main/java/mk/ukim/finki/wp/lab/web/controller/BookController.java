package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error,
                               @RequestParam(required = false) String text,
                               @RequestParam(required = false) String rating,
                               @RequestParam(required = false) Long authorId,
                               Model model){
        if (error != null) {
            model.addAttribute("error", error);
        }
        Double ratingNum = null;
        if (rating != null && !rating.isEmpty()){
            try { ratingNum = Double.parseDouble(rating); } catch (NumberFormatException ignored) {}
        }
        if (authorId != null) {
            model.addAttribute("books", bookService.listByAuthor(authorId));
        } else if (text != null || rating != null) {
            model.addAttribute("books", bookService.searchBooks(text, ratingNum));
        } else {
            model.addAttribute("books", bookService.listAll());
        }
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("text", text == null ? "" : text);
        model.addAttribute("rating", rating == null ? "" : rating);
        model.addAttribute("authorId", authorId);
        return "listBooks";
    }
    @GetMapping("/book-form")
    public String getAddBookPage(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "book-form"; // TODO
    }

    @PostMapping()
    public String saveBook(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId){
        bookService.saveBook(title, genre, averageRating, authorId);
        return "redirect:/books";
    }
    @GetMapping("/edit-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model) {
        if (id == null){
            return "redirect:/books?error=BookNotFound";
        }
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("authors", authorService.findAll());
        return "book-form"; // TODO
    }

    @PostMapping("/{id}")
    public String editBook(@PathVariable Long id,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId){
        bookService.updateBook(id, title, genre, averageRating, authorId);
        return "redirect:/books";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.delete(id);
        return "redirect:/books";
    }
}
