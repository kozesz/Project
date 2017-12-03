package ro.sci.cinema.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CinemaHall {
    private String name;
    private Seat seat;
    ArrayList<Seat> allSeats = new ArrayList<>();

    public void readMySeats() throws IOException {
        SeatCSVReader seatReader = new SeatCSVReader(new BufferedReader(new FileReader("Seats.csv")));
        List<Seat> mySeats = seatReader.readSeats();
        for (Seat seat : mySeats) {
            System.out.println("Row " + seat.getRow() + " Number " + seat.getNumber() + " Available " + seat.isAvailable());
            allSeats.add(seat);
        }
        seatReader.close();
    }

    ArrayList<Seat> availableSeats = new ArrayList<>();

    public List<Seat> availableSeats() {
        System.out.println("Available seats ");
        for (Seat seat : allSeats) {
            if (seat.isAvailable()) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }

    ArrayList<Seat> selectedSeats = new ArrayList<>();

    public ArrayList<Seat> selectSeat(int quantity) {
        while (selectedSeats.size() > quantity) ;
        {
            System.out.println("Please select your Row ");
            Scanner scanner = new Scanner(System.in);
            int row = Integer.parseInt(scanner.nextLine());
            System.out.println("Please select your Number ");
            int number = Integer.parseInt(scanner.nextLine());
            Seat seat = new Seat(row, number, true);
            if (availableSeats.contains(seat)) {
                availableSeats.remove(seat);
                selectedSeats.add(seat);
            } else System.out.println("The seat you selected is not available");
        }

        return selectedSeats;
    }
}
