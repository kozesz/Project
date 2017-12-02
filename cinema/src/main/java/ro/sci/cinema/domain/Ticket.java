package ro.sci.cinema.domain;

import java.util.Date;

public class Ticket extends AbstractModel {
    private Client client;

    private TicketType type;
    private Movie movie;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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


    @Override
    public String toString() {
        return "Ticket{" +
                "client=" + client +
                ", type=" + type +
                ", movie=" + movie +
                '}';
    }
}