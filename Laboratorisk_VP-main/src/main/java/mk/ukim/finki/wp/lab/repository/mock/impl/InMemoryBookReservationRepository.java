package mk.ukim.finki.wp.lab.repository.mock.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBookReservationRepository implements mk.ukim.finki.wp.lab.repository.mock.InMemoryBookReservationRepository {
    @Override
    public BookReservation save(BookReservation reservation) {
        DataHolder.reservations.add(reservation);
        return reservation;
    }
}
