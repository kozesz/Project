package ro.sci.cinema.domain;

import javafx.util.Pair;
import org.springframework.aop.target.LazyInitTargetSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SeatCSVReader extends Reader {
    private BufferedReader reader;

    public SeatCSVReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return reader.read(cbuf, off, len);
    }

    public List<Seat> readSeats(CinemaHall cinemaHall) {
        try {
            String line = reader.readLine();
            ArrayList<Seat> availableSeats = new ArrayList<>();

            while (line != null) {
                String[] tokens = line.split("/");
                String cinemaHallName = tokens[0];
                int row = Integer.parseInt(tokens[1]);
                int number = Integer.parseInt(tokens[2]);
                boolean available = Boolean.parseBoolean(tokens[3]);

                Seat seat = new Seat(row, number, available);
                if (cinemaHallName.equals(cinemaHall.getName()))
                availableSeats.add(seat);

                line = reader.readLine();
            }
            return availableSeats;

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return Collections.emptyList();
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

}


