package ro.sci.cinema.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CinemaHall {
    private String name;
    private ArrayList<Seat> allSeats = new ArrayList<>();
    private ArrayList<Seat> availableSeats = new ArrayList<>();
    private ArrayList<Seat> reservedSeats = new ArrayList<>();

    public void readAllSeats() throws IOException {
        SeatCSVReader seatReader = new SeatCSVReader(new BufferedReader(new FileReader("Seats.csv")));
        List<Seat> mySeats = seatReader.readSeats();
        for (Seat s : mySeats) {
            System.out.println("Row " + s.getRow() + " Number " + s.getNumber() + " Available " + s.isAvailable());
            allSeats.add(s);
        }
        seatReader.close();
    }

    public List<Seat> availableSeats() {
        System.out.println("Available seats ");
        for (Seat s : allSeats) {
            if (s.isAvailable()) {
                availableSeats.add(s);
            }
        }
        return availableSeats;
    }

    public boolean isTheSeatAvailable(Seat selectedSeat) {
        boolean a = false;
        if (availableSeats.contains(selectedSeat))
            a = true;
        return a;
    }


    public void reserveSeat(Seat selectedSeat) {
        availableSeats.remove(selectedSeat);
        reservedSeats.add(selectedSeat);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
