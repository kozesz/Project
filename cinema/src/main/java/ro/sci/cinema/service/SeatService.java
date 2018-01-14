package ro.sci.cinema.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.sci.cinema.dao.SeatDAO;
import ro.sci.cinema.domain.Seat;

import java.util.Collection;

public class SeatService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeatService.class);

    private SeatDAO sdao;

    public Collection<Seat> listAll() {
        return sdao.getAll();
    }

    public Collection<Seat> search( String query) {
        LOGGER.debug("Searching for " + query);
        return sdao.searchByName(query);
    }

    public boolean delete(Long id) {
        LOGGER.debug("Deleting employee for id: " + id);
        Seat seat = sdao.findById(id);
        if (seat != null) {
            sdao.delete(seat);
            return true;
        }
        return false;
    }

    public Seat get(Long id) {
        LOGGER.debug("Getting employee for id: " + id);
        return sdao.findById(id);

    }

    public void save(Seat seat) throws ValidationException {
        LOGGER.debug("Saving: " + seat);
        validate(seat);

        sdao.update(seat);
    }

    private void validate(Seat seat) throws ValidationException {}


    public SeatDAO getSdao() {
        return sdao;
    }

    public void setSdao(SeatDAO sdao) {
        this.sdao = sdao;
    }
}
