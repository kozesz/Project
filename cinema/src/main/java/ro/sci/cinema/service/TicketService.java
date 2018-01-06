package ro.sci.cinema.service;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import ro.sci.cinema.CinemaApplication;
import ro.sci.cinema.dao.CinemaDAO;
import ro.sci.cinema.dao.ProgramDAO;
import ro.sci.cinema.dao.TicketDAO;
import ro.sci.cinema.dao.MovieDAO;
import ro.sci.cinema.domain.CinemaHall;
import ro.sci.cinema.domain.Movie;
import ro.sci.cinema.domain.MoviesFromProgram;
import ro.sci.cinema.domain.Ticket;


public class TicketService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketService.class);

    private TicketDAO dao;
    private MovieDAO mdao;
    private CinemaDAO cdao;
    private ProgramDAO pdao;

    public Collection<Ticket> listAll() {
        return dao.getAll();
    }

    public Collection<Ticket> search(String query) {
        LOGGER.debug("Searching for " + query);
        return dao.searchByName(query);
    }

    public boolean delete(Long id) {
        LOGGER.debug("Deleting ticket for id: " + id);
        Ticket ticket = dao.findById(id);
        if (ticket != null) {
            dao.delete(ticket);
            return true;
        }
        return false;
    }

    public Ticket getTicket(Long id) {
        LOGGER.debug("Getting ticket for id: " + id);
        return dao.findById(id);
    }

    public Movie getMovie(Long id) {
        LOGGER.debug("Getting movie for id: " + id);
        return mdao.findById(id);

    }

    public void saveTicket(Ticket ticket) throws ValidationException {
        LOGGER.debug("Saving: " + ticket);
        validateTicket(ticket);
        dao.update(ticket);
    }

    public void saveMovie(Movie movie) throws ValidationException {
        LOGGER.debug("Saving: " + movie);
        mdao.update(movie);
    }

    public void saveProgram(MoviesFromProgram moviesFromProgram){
        LOGGER.debug("Saving: " + moviesFromProgram);
        pdao.update(moviesFromProgram);
    }

    public TicketDAO getDao() {
        return dao;
    }

    public void setDao(TicketDAO dao) {
        this.dao = dao;
    }

    private void validateTicket(Ticket ticket) throws ValidationException {
        Date currentDate = new Date();
        List<String> errors = new LinkedList<String>();

        if (ticket.getDate() == null) {
            errors.add("The date of the movie is empty.");
        } else {
            if (currentDate.after(ticket.getDate())) {
                errors.add("The date of the movie is in the past.");
            }
        }
        if (StringUtils.isEmpty(ticket.getMovie())) {
            errors.add("The movie is empty.");
        }
        if (ticket.getHour() == null) {
            errors.add("The hour of the movie is empty.");
        }
        if (StringUtils.isEmpty(ticket.getCinemaHall())) {
            errors.add("The cinemahall of the movie is empty.");
        }
        if (ticket.getQuantity() > 0) {
            errors.add("The quantity of the tickets is null.");
        }
        if (ticket.getSeats() == null) {
            errors.add("The hour of the movie is empty.");
        }
        if (StringUtils.isEmpty(ticket.getClient())) {
            errors.add("The client is empty.");
        }
    }


}
