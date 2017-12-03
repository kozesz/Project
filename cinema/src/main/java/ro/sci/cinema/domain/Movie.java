package ro.sci.cinema.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Movie {
    private String title;
    private MovieGenre genre;
    private MovieType type;
    private Date movieHour;
    private String rating;

    public void setCinemaHall(String cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public String getCinemaHall() {
        return cinemaHall;
    }

    private String cinemaHall;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getformatedDate() throws ParseException {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date d = dt.parse(String.valueOf(getDate()));
        return d;
    }


    public Movie(String title, MovieGenre genre, MovieType type, String rating) {
        this.title = title;
        this.genre = genre;
        this.type = type;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }

    public MovieType getType() {
        return type;
    }

    public void setType(MovieType type) {
        this.type = type;
    }

    public Date getMovieHour() {
        return movieHour;
    }

    public void setMovieHour(Date movieHour) {
        this.movieHour = movieHour;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre=" + genre +
                ", type=" + type +
                ", movieHour=" + movieHour +
                ", rating='" + rating + '\'' +
                '}';
    }
}
