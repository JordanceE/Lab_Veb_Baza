package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.jpa.BookRepository;
import mk.ukim.finki.wp.lab.repository.mock.InMemoryBookRepository;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> listByAuthor(Long authorId) {
        return bookRepository.findAllByAuthor_Id(authorId);
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return bookRepository.findByTitleContainingIgnoreCaseAndAverageRatingGreaterThanEqual(text, rating);
    }

    @Override
    public Book saveBook(String title, String genre, Double averageRating, Long authorId) {
        Author author = authorService.findById(authorId);
        Book book = new Book(title, genre, averageRating, author);
        return bookRepository.save(book);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public Book updateBook(Long id, String title, String genre, Double rating, Long authorId) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        book.setTitle(title);
        book.setGenre(genre);
        book.setAverageRating(rating);
        book.setAuthor(authorService.findById(authorId));
        return bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
