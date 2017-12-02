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
        CinemaHall c = new CinemaHall();

        c.readMySeats();


        cinema.selectMovieDay();

        cinema.readMyMovies();

        System.out.println(cinema.todaysMovies(cinema.selectMovieDay()));
        cinema.selectMovie();
        cinema.displayMovieHours();
        System.out.println(cinema.selectMovieHour());
        cinema.displayCinemaHall();
        //SpringApplication.run(CinemaApplication.class, args);
    }
}
