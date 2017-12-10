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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seat seat = (Seat) o;

        if (row != seat.row) return false;
        if (number != seat.number) return false;
        return available == seat.available;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + number;
        result = 31 * result + (available ? 1 : 0);
        return result;
    }
}
