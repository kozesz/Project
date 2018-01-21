package ro.sci.cinema.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.sci.cinema.dao.CinemaHallDAO;
import ro.sci.cinema.domain.CinemaHall;
import ro.sci.cinema.domain.*;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CinemaHallService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CinemaHallService.class);

    private CinemaHallDAO chdao;

    private ArrayList<Seat> allSeats = new ArrayList<>();
    private ArrayList<Seat> reservedSeats = new ArrayList<>();


    public ArrayList<Seat> readAllSeats(CinemaHall cinemaHall) throws IOException {
        SeatCSVReader seatReader = new SeatCSVReader(new BufferedReader(new FileReader("Seats.csv")));
        List<Seat> mySeats = seatReader.readSeats(cinemaHall);

        allSeats.removeAll(allSeats);
        for (Seat s : mySeats) {
            allSeats.add(s);
        }
        seatReader.close();
        return allSeats;
    }

    public void reserveSeat(Seat selectedSeat) {
        selectedSeat.setAvailable(false);
        reservedSeats.add(selectedSeat);
    }

    public ArrayList<Seat> getAllSeats() {
        return allSeats;
    }

    public ArrayList<Seat> getReservedSeats() {
        return reservedSeats;
    }

    public Collection<CinemaHall> listAll() {
        return chdao.getAll();
    }

    public Collection<CinemaHall> search(String query) {
        LOGGER.debug("Searching for " + query);
        return chdao.searchByName(query);
    }

    public boolean delete(Long id) {
        LOGGER.debug("Deleting cinemaHall for id: " + id);
        CinemaHall cinemaHall = chdao.findById(id);
        if (cinemaHall != null) {
            chdao.delete(cinemaHall);
            return true;
        }
        return false;
    }

    public CinemaHall get(Long id) {
        LOGGER.debug("Getting cinemaHall for id: " + id);
        return chdao.findById(id);

    }

    public void save(CinemaHall cinemaHall) throws ValidationException {
        LOGGER.debug("Saving: " + cinemaHall);
        validate(cinemaHall);

        chdao.update(cinemaHall);
    }

    private void validate(CinemaHall cinemaHall) throws ValidationException {
    }

    public CinemaHallDAO getChdao() {
        return chdao;
    }

    public void setChdao(CinemaHallDAO chdao) {
        this.chdao = chdao;
    }
}
