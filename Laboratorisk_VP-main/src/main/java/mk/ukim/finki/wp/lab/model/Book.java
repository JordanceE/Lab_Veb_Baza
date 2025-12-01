package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
public class Book {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String title;
    private String genre;
    private Double averageRating;
    @ManyToOne()
    private Author author;

    public Book(String title, String genre, Double averageRating) {
        this.title = title;
        this.genre = genre;
        this.averageRating = averageRating;
        this.Id = (long) (Math.random() * 1000);
    }

    public Book(Long id, String title, String genre, Double averageRating, Author author) {
        Id = id;
        this.title = title;
        this.genre = genre;
        this.averageRating = averageRating;
        this.author = author;
    }

    public Book(String title, String genre, Double averageRating, Author author) {

        this.title = title;
        this.genre = genre;
        this.averageRating = averageRating;
        this.author = author;
    }

    public Book() {
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
