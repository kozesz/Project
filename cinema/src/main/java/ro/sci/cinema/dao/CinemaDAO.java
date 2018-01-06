package ro.sci.cinema.dao;

import ro.sci.cinema.domain.Cinema;
import java.util.Collection;

public interface CinemaDAO extends BaseDAO<Cinema> {

    Collection<Cinema> searchByName(String query);
}
