package ro.sci.cinema.domain;

import org.springframework.aop.target.LazyInitTargetSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SeatCSVReader extends Reader {
    private BufferedReader reader;

    public SeatCSVReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return reader.read(cbuf, off, len);
    }

    public List<Seat> readSeats() {
        try {
            String line = reader.readLine();
            ArrayList<Seat> availableSeats = new ArrayList<>();

            while (line != null) {
                String[] tokens = line.split("/");

                int row = Integer.parseInt(tokens[0]);
                int number = Integer.parseInt(tokens[1]);
                boolean available = Boolean.parseBoolean(tokens[2]);

                Seat seat = new Seat(row, number, available);
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


