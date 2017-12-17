package ro.sci.cinema.domain;

import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.ValidationException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Cinema {
    private CinemaHall cinemaHall = new CinemaHall();
    private ArrayList<MoviesFromProgram> program = new ArrayList<>();
    private Ticket ticketInProgress = new Ticket();
    private ArrayList<Movie> allMovies = new ArrayList<>();
    private ArrayList<Movie> listMovies = new ArrayList<>();
    private ArrayList<Ticket> historyOfReservations = new ArrayList<>();

    public Date selectMovieDay() {
        while (ticketInProgress.getDate() == null) {
            try {
                System.out.println("Please enter the date (yyyy-MM-dd) ");
                Scanner scanner = new Scanner(System.in);
                String d = scanner.nextLine();
                SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dt.parse(d);
                System.out.println(dt.format(date));
                ticketInProgress.setDate(date);
            } catch (ParseException e) {
                System.out.println("The date is not valid.");
            }
        }
        return ticketInProgress.getDate();
    }

    public void readMyMovies() throws IOException, ParseException {
        MovieCSVReader movieReader = new MovieCSVReader(new BufferedReader(new FileReader("Movies.csv")));
        List<Movie> myMovies = movieReader.readMovies();
        for (Movie movie : myMovies) {
            allMovies.add(movie);
            System.out.println(movie);
        }
        movieReader.close();
    }

    public List<Movie> todaysMovies(Date date) {
        for (Movie movie : allMovies) {
            if (date.equals(ticketInProgress.getDate())) {
                listMovies.add(movie);
            }
        }
        return listMovies;
    }

    public Movie selectMovie() {
        while (ticketInProgress.getMovie() == null) {
            try {
                System.out.println("Please select the movie by title: ");
                Scanner scanner = new Scanner(System.in);
                String title = scanner.nextLine();
                for (Movie movie : listMovies) {
                    if (Objects.equals(title, movie.getTitle())) {
                        ticketInProgress.setMovie(movie);
                        System.out.println(ticketInProgress.getMovie());
                    }
                }
            } catch (Exception e) {
                System.out.println("The movie title is incorrect.");
            }
        }
        return ticketInProgress.getMovie();
    }

    public void readProgram() throws ParseException, FileNotFoundException {
        MoviesProgramForCurrentWeekCSVReader moviesProgramForCurrentWeekCSVReader = new MoviesProgramForCurrentWeekCSVReader(new BufferedReader(new FileReader("MoviesProgramForCurrentWeek.csv")));
        for (MoviesFromProgram moviesFromProgram : moviesProgramForCurrentWeekCSVReader.readMoviesProgram()) {
            program.add(moviesFromProgram);
        }
    }

    public void displayMovieHours() throws IOException, ParseException {
        for (MoviesFromProgram program : program) {
            if (Objects.equals(ticketInProgress.getMovie().getTitle(), program.getTitle()) && ticketInProgress.getDate().equals(program.getDate())) {
                System.out.println(program.getHour());
            }
        }
    }

    public Date selectMovieHour() {
        while (ticketInProgress.getHour() == null) {
            try {
                System.out.println("Please select the hour (HH:mm) ");
                Scanner scanner = new Scanner(System.in);
                String h = scanner.nextLine();
                SimpleDateFormat hr = new SimpleDateFormat("HH:mm");
                Date hour = hr.parse(h);
                for (MoviesFromProgram p : program) {
                    if (p.getTitle().equals(ticketInProgress.getMovie().getTitle()) && p.getHour().equals(hour))
                        ticketInProgress.setHour(hour);
                }
            } catch (ParseException e) {
                System.out.println("The hour is incorrect.");
            }
        }
        return ticketInProgress.getHour();
    }

    public void displayCinemaHall() throws ParseException, FileNotFoundException {
        for (MoviesFromProgram p : program) {
            if (p.getTitle().equals(ticketInProgress.getMovie().getTitle()) && ticketInProgress.getDate().equals(p.getDate()) && ticketInProgress.getHour().equals(p.getHour())) {

                ticketInProgress.setCinemaHall(p.getHall());
                System.out.println(ticketInProgress.getCinemaHall().getName());
            }
        }
    }

    public void getAvailableSeats() throws IOException {
        cinemaHall.readAllSeats();
        cinemaHall.availableSeats();
    }

    public void displayAvailableSeats(){
        System.out.println(cinemaHall.getAvailableSeats());
    }

    public int selectTicketQuantity() throws IOException {
        getAvailableSeats();
        while (ticketInProgress.getQuantity() == 0) {
            try {
                System.out.println("Please add ticket quantity ");
                Scanner scanner = new Scanner(System.in);
                int q = Integer.parseInt(scanner.nextLine());
                if (q > cinemaHall.getAvailableSeats().size()) {
                    System.out.println("We have only " + cinemaHall.getAvailableSeats().size() + " seats.");
                } else {
                    ticketInProgress.setQuantity(q);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
        return ticketInProgress.getQuantity();

    }

    public ArrayList<TicketType> selectTicketType() {
        while (ticketInProgress.getTypes().size() != ticketInProgress.getQuantity()) {
            try {
                for (int i = 0; i < ticketInProgress.getQuantity(); i++) {
                    System.out.println("Please select the ticket type (ADULT, STUDENT, CHILD, PENSIONER)");
                    Scanner scanner = new Scanner(System.in);
                    ticketInProgress.addTypes(TicketType.valueOf(String.valueOf(scanner.nextLine())));
                }
            } catch (Exception e) {
                System.out.println("The type is incorrect.");
            }
        }
        return ticketInProgress.getTypes();
    }

    public void selectSeats() {
        while (ticketInProgress.getSeats().size() < ticketInProgress.getQuantity()) {
            System.out.println("Please select your Row ");
            Scanner scanner = new Scanner(System.in);
            int row = Integer.parseInt(scanner.nextLine());
            System.out.println("Please select your Number ");
            int number = Integer.parseInt(scanner.nextLine());
            Seat temporarySeat = new Seat(row, number, true);
            if (cinemaHall.isTheSeatAvailable(temporarySeat)) {
                ticketInProgress.addSeats(temporarySeat);
            } else {
                System.out.println("The seat you selected is not available, please try again.");
            }
        }
    }

    public Client addClient() {
        System.out.println("Please add your first name ");
        Scanner scanner = new Scanner(System.in);
        String firstName = scanner.nextLine();
        System.out.println("Please add your last name ");
        String lastName = scanner.nextLine();
        System.out.println("Please add your phone number ");
        String phoneNumber = scanner.nextLine();
        System.out.println("Please add your e-mail ");
        String email = scanner.nextLine();
        Client client = new Client(firstName, lastName, phoneNumber, email);
        ticketInProgress.setClient(client);
        return ticketInProgress.getClient();
    }

    public void displayReservation() {
        System.out.println("Date " + ticketInProgress.getDate());
        System.out.println("Movie " + ticketInProgress.getMovie());
        System.out.println("Hour " + ticketInProgress.getHour());
        System.out.println("Ticket quantity " + ticketInProgress.getQuantity());
        System.out.println("Ticket types " + ticketInProgress.getTypes());
        System.out.println("CinemaHall " + ticketInProgress.getCinemaHall().getName());
        System.out.println("Seats " + ticketInProgress.getSeats());
        System.out.println("Client" + ticketInProgress.getClient());
    }

    public void reserveTicket() {
        System.out.println("Do you want to reserve the ticket? Y/N");
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextLine().equals("Y")) {
            historyOfReservations.add(ticketInProgress);
            reserveTheSelectedSeats();
            ticketInProgress = null;
            System.out.println("Congratulation, you reserved a ticket" + historyOfReservations);
        } else System.out.println("Thank you for stopping by. Have a nice day!");
    }

    public void reserveTheSelectedSeats() {
        for (Seat s : ticketInProgress.getSeats()) {
            cinemaHall.reserveSeat(s);
        }
    }


}
