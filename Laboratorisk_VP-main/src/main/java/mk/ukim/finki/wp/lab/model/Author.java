package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String surname;
    private String country;
    private String biography;
    @OneToMany(mappedBy = "author")
    private List<Book> books = new ArrayList<>();

    public Author(String name, String surname, String country, String biography) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.biography = biography;

    }

    public Author(Long id, String name, String surname, String country, String biography) {
        Id = id;
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.biography = biography;
    }

    public Author() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
