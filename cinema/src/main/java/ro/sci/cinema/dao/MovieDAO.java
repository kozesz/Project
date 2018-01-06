package ro.sci.cinema.dao;

import ro.sci.cinema.domain.Movie;

import java.util.Collection;

public interface MovieDAO extends BaseDAO<Movie> {

    Collection<Movie> searchByName(String query);
}
