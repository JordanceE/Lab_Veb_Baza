package mk.ukim.finki.wp.lab.repository.mock;

import mk.ukim.finki.wp.lab.model.Author;

import java.util.List;


public interface InMemoryAuthorRepository {
    public List<Author> findAll();

    public Author findById(Long authorId);
}
