package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;

public interface BookService {
    List<Book> listByAuthor(Long authorId);
    List<Book> listAll();
    List<Book> searchBooks(String text, Double rating);
    Book saveBook(String title, String genre, Double averageRating, Long authorId);
    Book findById(Long id);
    Book updateBook(Long id, String title, String genre, Double rating, Long authorId);
    void delete(Long id);
}
