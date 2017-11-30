package ro.sci.cinema.domain;

import java.util.Date;

public class Ticket extends AbstractModel {
    private Client client;
    private String seat;
    private TicketType type;
    private Movie movie;
    private Cinema cinema;


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (client != null ? !client.equals(ticket.client) : ticket.client != null) return false;
        if (seat != null ? !seat.equals(ticket.seat) : ticket.seat != null) return false;
        if (type != ticket.type) return false;
        if (movie != null ? !movie.equals(ticket.movie) : ticket.movie != null) return false;
        return cinema != null ? cinema.equals(ticket.cinema) : ticket.cinema == null;
    }

    @Override
    public int hashCode() {
        int result = client != null ? client.hashCode() : 0;
        result = 31 * result + (seat != null ? seat.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (movie != null ? movie.hashCode() : 0);
        result = 31 * result + (cinema != null ? cinema.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "client=" + client +
                ", seat='" + seat + '\'' +
                ", type=" + type +
                ", movie=" + movie +
                ", cinema=" + cinema +
                '}';
    }
}