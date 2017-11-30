package ro.sci.cinema.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CinemaHall {
    private String name;

    private Seat seat;

    public CinemaHall(String name) {
        this.name = name;
    }

    public void readMySeats() throws IOException {
        SeatCSVReader seatReader = new SeatCSVReader(new BufferedReader(new FileReader("Seats.csv")));
        List<Seat> mySeats = seatReader.readSeats();
        for (Seat seat : mySeats){
            System.out.println("Row " + seat.getRow() + " Number " + seat.getNumber() + " Available " + seat.isAvailable());
        }
        seatReader.close();
    }
}
