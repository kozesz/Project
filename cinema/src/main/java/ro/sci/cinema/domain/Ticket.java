package ro.sci.cinema.domain;

import java.util.ArrayList;
import java.util.Date;

public class Ticket extends AbstractModel {
    private Date date;
    private Movie movie;
    private Date hour;
    private int quantity;
    private ArrayList<TicketType> type;
    private ArrayList<Seat> seats;
    private Client client;

    public Ticket(Client client, int quantity, ArrayList<TicketType> type, Movie movie) {
        this.client = client;
        this.type = type;
        this.movie = movie;
        this.quantity = quantity;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ArrayList<TicketType> getType() {
        return type;
    }

    public void setType(ArrayList<TicketType> type) {
        this.type = type;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "client=" + client +
                ", type=" + type +
                ", movie=" + movie +
                ", quantity=" + quantity +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getHour() {
        return hour;
    }

    public void setHour(Date hour) {
        this.hour = hour;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }
}