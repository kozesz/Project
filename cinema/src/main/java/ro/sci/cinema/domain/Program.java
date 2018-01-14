package ro.sci.cinema.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Program extends AbstractModel{
    private Date date;
    private Movie movie;
    private Date hour;
    private CinemaHall hall;

    public Program(Date date, Movie movie, Date hour, CinemaHall hall) {
        this.date = date;
        this.movie = movie;
        this.hour = hour;
        this.hall = hall;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Date getHour() {
        return hour;
    }

    public void setHour(Date hour) {
        this.hour = hour;
    }

    public CinemaHall getHall() {
        return hall;
    }

    public void setHall(CinemaHall hall) {
        this.hall = hall;
    }
}
