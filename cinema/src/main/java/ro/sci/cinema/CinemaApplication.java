package ro.sci.cinema;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import ro.sci.cinema.domain.Cinema;
import ro.sci.cinema.domain.CinemaHall;

import java.io.IOException;
import java.text.ParseException;

@SpringBootApplication
public class CinemaApplication {

    public static void main(String[] args) throws ParseException, IOException {
        Cinema cinema = new Cinema();

        cinema.readProgram();
        cinema.readMyMovies();

        System.out.println(cinema.todaysMovies(cinema.selectMovieDay()));
        cinema.selectMovie();
        cinema.displayMovieHours();
        System.out.println(cinema.selectMovieHour());
        cinema.displayCinemaHall();
        cinema.selectTicketQuantity();
        System.out.println(cinema.selectTicketType());
        cinema.displayAvailableSeats();
        cinema.selectSeats();
        cinema.addClient();
        cinema.displayReservation();
        cinema.reserveTicket();


        //SpringApplication.run(CinemaApplication.class, args);
    }
}
