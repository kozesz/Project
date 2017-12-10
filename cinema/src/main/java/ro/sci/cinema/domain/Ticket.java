package ro.sci.cinema.domain;

import java.util.ArrayList;
import java.util.Date;

public class Ticket extends AbstractModel {
    private Date date;
    private Movie movie;
    private Date hour;
    private CinemaHall cinemaHall;
    private int quantity;
    private ArrayList<TicketType> types = new ArrayList<>();
    private ArrayList<Seat> seats = new ArrayList<>();
    private Client client;


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ArrayList<TicketType> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<TicketType> type) {
        this.types = type;
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

    public void addSeats(Seat s){
        seats.add(s);
    }

    public void addTypes(TicketType t){
        types.add(t);
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "  \ndate=" + date +
                ", \nmovie=" + movie +
                ", \nhour=" + hour +
                ", \nquantity=" + quantity +
                ", \ntypes=" + types +
                ", \nseats=" + seats +
                ", \nclient=" + client +
                '}';
    }
}