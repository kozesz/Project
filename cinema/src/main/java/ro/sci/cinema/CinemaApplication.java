package ro.sci.cinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ro.sci.cinema.domain.Cinema;
import ro.sci.cinema.domain.CinemaHall;

import java.io.IOException;
import java.text.ParseException;

@SpringBootApplication
public class CinemaApplication {

	public static void main(String[] args) throws ParseException, IOException {
		Cinema cinema = new Cinema();
		CinemaHall c  = new CinemaHall("Hall-1");
		try {
			c.readMySeats();
		} catch (IOException e) {
			e.printStackTrace();
		}

		cinema.selectMovieDay();

		cinema.readMyMovies();

		System.out.println(cinema.todaysMovies(cinema.selectMovieDay()));

		//SpringApplication.run(CinemaApplication.class, args);
	}
}
