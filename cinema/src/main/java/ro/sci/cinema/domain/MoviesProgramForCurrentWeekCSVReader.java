package ro.sci.cinema.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.io.Reader;
import java.util.List;

public class MoviesProgramForCurrentWeekCSVReader extends Reader {

    private BufferedReader reader;

    public MoviesProgramForCurrentWeekCSVReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return reader.read(cbuf, off, len);
    }

    public List<MoviesFromProgram> readMoviesProgram() throws ParseException {
        try {
            String line = reader.readLine();
            ArrayList<MoviesFromProgram> weeklyProgram = new ArrayList<>();

            while (line != null) {
                String[] tokens = line.split("/");

                String s = tokens[0];
                SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dt.parse(s);

                String title = tokens[1];
                String genre = String.valueOf(MovieGenre.valueOf(tokens[2]));
                String type = String.valueOf(MovieType.valueOf(tokens[3]));
                String rating = tokens[4];

                Movie movie = new Movie(title, MovieGenre.valueOf(genre), MovieType.valueOf(type), rating);

                String h = tokens[5];
                SimpleDateFormat hr = new SimpleDateFormat("HH:mm");
                Date hour = hr.parse(h);
                String hallName = tokens[6];
                CinemaHall c = new CinemaHall();
                c.setName(hallName);

                MoviesFromProgram moviesFromProgram = new MoviesFromProgram(date, movie, hour, c);
                weeklyProgram.add(moviesFromProgram);
                line = reader.readLine();

            }
            return weeklyProgram;

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
