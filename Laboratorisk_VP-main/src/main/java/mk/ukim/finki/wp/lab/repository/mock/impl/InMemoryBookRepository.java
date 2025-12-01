package mk.ukim.finki.wp.lab.repository.mock.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryBookRepository implements mk.ukim.finki.wp.lab.repository.mock.InMemoryBookRepository {
    @Override
    public List<Book> findAll() {
        return DataHolder.books;
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        if (text !=null && !text.isEmpty() && rating!=null){
            return DataHolder.books.stream().filter(c -> c.getTitle().equals(text) && c.getAverageRating() >= rating).toList();
        }
        if (rating==null && text != null && !text.isEmpty()){
            return DataHolder.books.stream().filter(c -> c.getTitle().equals(text)).toList();
        }
        if ((text == null || text.isEmpty()) && rating != null){
            return DataHolder.books.stream().filter(c -> c.getAverageRating() >= rating).toList();
        }
        return findAll();
    }

    @Override
    public Book saveBook(Book book) {
        delete(book.getId());
        DataHolder.books.add(book);
        return book;
    }

    @Override
    public Book findById(Long id) {
        return DataHolder.books.stream().filter(b -> b.getId() .equals(id)).findFirst().orElseThrow();
    }

    @Override
    public void delete(Long id) {
        DataHolder.books.removeIf(m -> m.getId().equals(id));
    }
}
