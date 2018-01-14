package ro.sci.cinema.dao.inmemory;

import org.springframework.util.StringUtils;
import ro.sci.cinema.dao.SeatDAO;
import ro.sci.cinema.domain.Seat;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class IMSeatDAO extends IMBaseDAO<Seat> implements SeatDAO {

    public Collection<Seat> searchByName(String query) {
        if (StringUtils.isEmpty(query)) {
            return getAll();
        }

        Collection<Seat> all = new LinkedList<Seat>(getAll());
        for (Iterator<Seat> it = all.iterator(); it.hasNext();) {
            Seat seat = it.next();
            String ss = String.valueOf(seat.getRow() + " " + seat.getNumber() + " " + seat.isAvailable());
            if (!ss.toLowerCase().contains(query.toLowerCase())) {
                it.remove();
            }
        }
        return all;
    }
}
