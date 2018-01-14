package ro.sci.cinema.dao.inmemory;

import org.springframework.util.StringUtils;
import ro.sci.cinema.dao.CinemaHallDAO;
import ro.sci.cinema.domain.CinemaHall;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class IMCinemaHallDAO extends IMBaseDAO<CinemaHall> implements CinemaHallDAO{

    public Collection<CinemaHall> searchByName(String query) {
        if (StringUtils.isEmpty(query)) {
            return getAll();
        }

        Collection<CinemaHall> all = new LinkedList<CinemaHall>(getAll());
        for (Iterator<CinemaHall> it = all.iterator(); it.hasNext(); ) {
            CinemaHall cinemaHall = it.next();
            String ss = cinemaHall.getName();
            if (!ss.toLowerCase().contains(query.toLowerCase())) {
                it.remove();
            }
        }
        return all;
    }
}
