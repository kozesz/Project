package ro.sci.cinema.domain;

public class Seat {
    private int row;
    private int number;
    private boolean available;

    public Seat(int row, int number, boolean available) {
        this.row = row;
        this.number = number;
        this.available = available;
    }


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "row=" + row +
                ", number=" + number +
                ", available=" + available +
                '}';
    }
}
