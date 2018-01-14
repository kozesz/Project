package ro.sci.cinema.dao;

import ro.sci.cinema.domain.CinemaHall;

import java.util.Collection;

public interface CinemaHallDAO extends BaseDAO<CinemaHall> {

    Collection<CinemaHall> searchByName(String query);
}
