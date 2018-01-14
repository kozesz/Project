package ro.sci.cinema.dao.inmemory;

import org.springframework.util.StringUtils;
import ro.sci.cinema.dao.BaseDAO;
import ro.sci.cinema.dao.MovieDAO;
import ro.sci.cinema.domain.Movie;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class IMMovieDAO extends IMBaseDAO<Movie> implements MovieDAO {

    public Collection<Movie> searchByName(String query) {
        if (StringUtils.isEmpty(query)) {
            return getAll();
        }

        Collection<Movie> all = new LinkedList<Movie>(getAll());
        for (Iterator<Movie> it = all.iterator(); it.hasNext();) {
            Movie movie = it.next();
            String ss = String.valueOf(movie.getTitle());
            if (!ss.toLowerCase().contains(query.toLowerCase())) {
                it.remove();
            }
        }
        return all;
    }
}
