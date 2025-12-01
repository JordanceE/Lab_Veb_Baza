package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping
    public String getAuthorsPage(Model model){
        model.addAttribute("authors", authorService.findAll());
        return "listAuthors";
    }
    @GetMapping("/author-form")
    public String getAddAuthorPage(Model model){
        return "author-form";
    }
    @PostMapping()
    public String saveAuthor(@RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String country,
                             @RequestParam String biography){
        authorService.saveAuthor(name, surname, country, biography);
        return "redirect:/authors";
    }
}
