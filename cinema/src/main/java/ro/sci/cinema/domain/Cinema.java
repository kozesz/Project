package ro.sci.cinema.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Cinema {
    private Client client;
    private CinemaName name;
    private Date movieDay;

    private Ticket ticket;

    private Movie movie;

    public Date selectMovieDay() throws ParseException {
        System.out.println("Please enter the date (yyyy-MM-dd) ");
        Scanner scanner = new Scanner(System.in);
        String d = scanner.nextLine();

        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dt.parse(d);

        System.out.println(dt.format(date));

        return date;
    }

    public void readMySeats() throws IOException {
        SeatCSVReader seatReader = new SeatCSVReader(new BufferedReader(new FileReader("Seats.csv")));
        List<Seat> mySeats = seatReader.readSeats();
        for (Seat seat : mySeats) {
            System.out.println("Row " + seat.getRow() + " Number " + seat.getNumber() + " Available " + seat.isAvailable());
        }
        seatReader.close();
    }

    ArrayList<Movie> allMovies = new ArrayList<>();

    public void readMyMovies() throws IOException, ParseException {
        MovieCSVReader movieReader = new MovieCSVReader(new BufferedReader(new FileReader("Movies.csv")));
        List<Movie> myMovies = movieReader.readMovies();
        for (Movie movie : myMovies) {
            allMovies.add(movie);
            System.out.println("Title " + movie.getTitle() + " Genre " + movie.getGenre() + " Type " + movie.getType() + " Rating " + movie.getRating());
        }
        movieReader.close();
    }

    public List<Movie> todaysMovies(Date date) throws ParseException {
        ArrayList<Movie> listMovies = new ArrayList<>();

        for (Movie movie : allMovies) {
            if (date.equals(movie.getDate())) {
                listMovies.add(movie);
                System.out.println(movie);
            }
        }
        return listMovies;
    }

    public Movie selectMovie() {

        return null;
    }

    public Date selectMovieHour() {
        // the Hall is selected by default
        return null;
    }

    public List<Seat> availableSeats() {

        return null;
    }

    public Seat selectSeat() {
        // from availableSeats()
        return null;
    }

    public Client addClient() {
        // Client adds information
        return null;
    }

    public Ticket reserveTicket() {

        return null;
    }

    public void displayReservation() {

    }


}
