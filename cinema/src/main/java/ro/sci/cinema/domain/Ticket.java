package ro.sci.cinema.domain;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;

public class Ticket extends AbstractModel {
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @NotNull
    private Movie movie;

    @NotNull
    @DateTimeFormat(pattern = "HH-mm")
    private Date hour;

    @NotNull
    private CinemaHall cinemaHall;

    @NotNull
    private int quantity;

    @NotEmpty
    private ArrayList<TicketType> types = new ArrayList<>();

    @NotEmpty
    private ArrayList<Seat> seats = new ArrayList<>();

    @NotEmpty
    private Client client;


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

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ArrayList<TicketType> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<TicketType> types) {
        this.types = types;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (quantity != ticket.quantity) return false;
        if (date != null ? !date.equals(ticket.date) : ticket.date != null) return false;
        if (movie != null ? !movie.equals(ticket.movie) : ticket.movie != null) return false;
        if (hour != null ? !hour.equals(ticket.hour) : ticket.hour != null) return false;
        if (cinemaHall != null ? !cinemaHall.equals(ticket.cinemaHall) : ticket.cinemaHall != null) return false;
        if (types != null ? !types.equals(ticket.types) : ticket.types != null) return false;
        if (seats != null ? !seats.equals(ticket.seats) : ticket.seats != null) return false;
        return client != null ? client.equals(ticket.client) : ticket.client == null;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (movie != null ? movie.hashCode() : 0);
        result = 31 * result + (hour != null ? hour.hashCode() : 0);
        result = 31 * result + (cinemaHall != null ? cinemaHall.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (types != null ? types.hashCode() : 0);
        result = 31 * result + (seats != null ? seats.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "date=" + date +
                ", movie=" + movie +
                ", hour=" + hour +
                ", cinemaHall=" + cinemaHall +
                ", quantity=" + quantity +
                ", types=" + types +
                ", seats=" + seats +
                ", client=" + client +
                '}';
    }
}