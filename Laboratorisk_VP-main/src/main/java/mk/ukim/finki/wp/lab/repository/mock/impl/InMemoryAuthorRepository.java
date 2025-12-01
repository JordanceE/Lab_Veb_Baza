package mk.ukim.finki.wp.lab.repository.mock.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryAuthorRepository implements mk.ukim.finki.wp.lab.repository.mock.InMemoryAuthorRepository {


    @Override
    public List<Author> findAll() {
        return DataHolder.authors;
    }

    @Override
    public Author findById(Long authorId) {
        return DataHolder.authors.stream().filter(author -> authorId.equals(author.getId())).findFirst().orElseThrow();
    }
}
