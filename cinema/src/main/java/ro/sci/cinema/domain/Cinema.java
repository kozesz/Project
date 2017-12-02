package ro.sci.cinema.domain;

import javax.xml.bind.SchemaOutputResolver;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Cinema {
    private Client client;
    private CinemaName name;
    private Date movieDay;

    private Ticket ticket;

    private Movie movie;
    public Movie selectedMovie;
    public Date selectedDate;

    public Date selectMovieDay() throws ParseException {
        System.out.println("Please enter the date (yyyy-MM-dd) ");
        Scanner scanner = new Scanner(System.in);
        String d = scanner.nextLine();

        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dt.parse(d);

        System.out.println(dt.format(date));
        selectedDate = date;


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
            System.out.println(movie);
        }
        movieReader.close();
    }

    ArrayList<Movie> listMovies = new ArrayList<>();

    public List<Movie> todaysMovies(Date date) throws ParseException {
        for (Movie movie : allMovies) {
            if (date.equals(selectedDate)) {
                listMovies.add(movie);

            }
        }
        return listMovies;
    }


    public Movie selectMovie() {
        System.out.println("Please select the movie by title: ");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        for (Movie movie : listMovies) {
            if (Objects.equals(title, movie.getTitle())) {
                selectedMovie = movie;
                System.out.println(selectedMovie);
            }
        }
        return selectedMovie;
    }

    ArrayList<Date> selectedMovieHours = new ArrayList<>();

    public void displayMovieHours() throws IOException, ParseException {
        MoviesProgramForCurrentWeekCSVReader moviesProgramForCurrentWeekCSVReader = new MoviesProgramForCurrentWeekCSVReader(new BufferedReader(new FileReader("MoviesProgramForCurrentWeek.csv")));
        for (MoviesFromProgram moviesFromProgram : moviesProgramForCurrentWeekCSVReader.readMoviesProgram()) {
            if (Objects.equals(selectedMovie.getTitle(), moviesFromProgram.getTitle()) && selectedDate.equals(moviesFromProgram.getDate())) {
                System.out.println(moviesFromProgram.getHour());
                selectedMovieHours.add(moviesFromProgram.getHour());
            }
        }
    }

    public Date selectMovieHour() throws ParseException {
        System.out.println("Please select the hour (HH:mm) ");
        Scanner scanner = new Scanner(System.in);
        String h = scanner.nextLine();
        SimpleDateFormat hr = new SimpleDateFormat("HH:mm");
        Date hour = hr.parse(h);
        if (selectedMovieHours.contains(hour)) {
            selectedMovie.setMovieHour(hour);
        } else System.out.println("Invalid hour");
        return selectedMovie.getMovieHour();
    }

    public void displayCinemaHall() throws ParseException, FileNotFoundException {
        MoviesProgramForCurrentWeekCSVReader moviesProgramForCurrentWeekCSVReader = new MoviesProgramForCurrentWeekCSVReader(new BufferedReader(new FileReader("MoviesProgramForCurrentWeek.csv")));
        for (MoviesFromProgram moviesFromProgram : moviesProgramForCurrentWeekCSVReader.readMoviesProgram()) {
            if (Objects.equals(selectedMovie.getTitle(), moviesFromProgram.getTitle()) && selectedDate.equals(moviesFromProgram.getDate()) && selectedMovie.getMovieHour().equals(moviesFromProgram.getHour())) {
                selectedMovie.setCinemaHall(moviesFromProgram.getHall());
                System.out.println(selectedMovie.getCinemaHall());
            }
        }
    }

    public void selectTicket() {

    }

    public Seat selectSeat() {
        System.out.println("Please select your seats ");
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
