package ro.sci.cinema.dao;

import ro.sci.cinema.domain.Program;
import java.util.Collection;

public interface ProgramDAO extends BaseDAO<Program> {

    Collection<Program> searchByName(String query);
}
