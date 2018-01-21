package ro.sci.cinema.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import ro.sci.cinema.dao.*;
import ro.sci.cinema.domain.*;


public class TicketService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TicketService.class);

    private TicketDAO tdao;
    private ArrayList<Program> program = new ArrayList<>();

    public ArrayList<Movie> readMyMovies() throws IOException, ParseException {
        MovieCSVReader movieReader = new MovieCSVReader(new BufferedReader(new FileReader("Movies.csv")));
        List<Movie> myMovies = movieReader.readMovies();
        ArrayList<Movie> allMovies = new ArrayList<>();
        for (Movie movie : myMovies) {
            allMovies.add(movie);
        }
        movieReader.close();
        return allMovies;
    }

    public Collection<Program> programOfTheWeek() throws ParseException, FileNotFoundException {
        ProgramCSVReader programCSVReader = new ProgramCSVReader(new BufferedReader(new FileReader("MoviesProgramForCurrentWeek.csv")));
        List<Program> programOfTheWeek = new ArrayList<>();
        for (Program moviesFromProgram : programCSVReader.readProgram()) {
            programOfTheWeek.add(moviesFromProgram);
            program.add(moviesFromProgram);

        }
        return programOfTheWeek;
    }

    public ArrayList<Program> getProgramForToday(Date date) throws  ParseException {
        ArrayList<Program> ppp = new ArrayList<>();
        for (Program p : program){
            if (p.getDate().equals(date))
                ppp.add(p);
        }
        return ppp;
    }

    public Collection<TicketType> getTicketTypes() {
        Collection<TicketType> allTypes = new ArrayList<>();
        for (TicketType t : TicketType.values()) {
            allTypes.add(t);
        }
        return allTypes;
    }

    public Collection<Ticket> listAll() {
        return tdao.getAll();
    }

    public Collection<Ticket> search(String query) {
        LOGGER.debug("Searching for " + query);
        return tdao.searchByName(query);
    }

    public boolean delete(Long id) {
        LOGGER.debug("Deleting ticket for id: " + id);
        Ticket ticket = tdao.findById(id);
        if (ticket != null) {
            tdao.delete(ticket);
            return true;
        }
        return false;
    }

    public Ticket get(Long id) {
        LOGGER.debug("Getting ticket for id: " + id);
        return tdao.findById(id);
    }

    public void save(Ticket ticket) throws ValidationException {
        LOGGER.debug("Saving: " + ticket);
        //validate(ticket);
        tdao.update(ticket);
    }

    private void validate(Ticket ticket) throws ValidationException {
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

    public TicketDAO getDao() {
        return tdao;
    }

    public void setDao(TicketDAO dao) {
        this.tdao = dao;
    }


}
