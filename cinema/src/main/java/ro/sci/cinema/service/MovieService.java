package ro.sci.cinema.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.sci.cinema.dao.MovieDAO;
import ro.sci.cinema.domain.*;

import java.util.Collection;

public class MovieService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieService.class);

    private MovieDAO mdao;

    public Collection<Movie> listAll() {
        return mdao.getAll();
    }

    public Collection<Movie> search( String query) {
        LOGGER.debug("Searching for " + query);
        return mdao.searchByName(query);
    }

    public boolean delete(Long id) {
        LOGGER.debug("Deleting movie for id: " + id);
        Movie movie = mdao.findById(id);
        if (movie != null) {
            mdao.delete(movie);
            return true;
        }
        return false;
    }

    public Movie get(Long id) {
        LOGGER.debug("Getting movie for id: " + id);
        return mdao.findById(id);

    }

    public void save(Movie movie) throws ValidationException {
        LOGGER.debug("Saving: " + movie);
        validate(movie);

        mdao.update(movie);
    }

    private void validate(Movie movie) throws ValidationException {}


    public MovieDAO getMdao() {
        return mdao;
    }

    public void setMdao(MovieDAO mdao) {
        this.mdao = mdao;
    }
}
