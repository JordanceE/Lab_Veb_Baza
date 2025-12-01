package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();
    public static List<Author> authors = new ArrayList<>();
    @PostConstruct
    public void init(){
        authors.add(new Author("Tod", "Jefferson", "Germany", "madeup"));
        authors.add(new Author("Brad", "Riesling", "Austria", "wine"));
        authors.add(new Author("John", "Johnson", "USA", "joking"));
        books.add(new Book("Moneyball", "real", 4.69, authors.get(0)));
        books.add(new Book("Basketball", "real", 4.5, authors.get(0)));
        books.add(new Book("Honeyball", "fake", 4.89, authors.get(0)));
        books.add(new Book("Horseball", "cgi", 4.29, authors.get(1)));
        books.add(new Book("Football", "fake", 4.69, authors.get(1)));
        books.add(new Book("Soccer", "real", 4.29, authors.get(1)));
        books.add(new Book("Tennis", "extraordinary", 3.69, authors.get(2)));
        books.add(new Book("Rugby", "cgi", 2.75, authors.get(2)));
        books.add(new Book("Ping pong", "fake", 4.09, authors.get(2)));
        books.add(new Book("Dodgeball", "real", 4.49, authors.get(2)));

    }
}
