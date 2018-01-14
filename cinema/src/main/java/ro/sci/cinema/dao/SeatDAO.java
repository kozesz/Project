package ro.sci.cinema.dao;

import ro.sci.cinema.domain.Seat;

import java.util.Collection;

public interface SeatDAO extends BaseDAO<Seat> {

    Collection<Seat> searchByName(String query);
}
