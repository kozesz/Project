package ro.sci.cinema.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MoviesFromProgram {

    private Date date;
    private String title;
    private Date hour;
    private String hall;


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getHour() {
        return hour;
    }

    public void setHour(Date hour) {
        this.hour = hour;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }


    public MoviesFromProgram(Date date, String title, Date hour, String hall) {
        this.date = date;
        this.title = title;
        this.hour = hour;
        this.hall = hall;
    }
}

