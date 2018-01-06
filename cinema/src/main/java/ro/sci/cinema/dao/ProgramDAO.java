package ro.sci.cinema.dao;

import ro.sci.cinema.domain.MoviesFromProgram;

import java.util.Collection;

public interface ProgramDAO extends BaseDAO {

    Collection<MoviesFromProgram> searchByName(String query);
}
