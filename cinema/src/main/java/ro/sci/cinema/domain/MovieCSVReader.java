package ro.sci.cinema.domain;

import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MovieCSVReader extends Reader {
    private BufferedReader reader;

    public MovieCSVReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return reader.read(cbuf, off, len);
    }

    public List<Movie> readMovies() throws ParseException {
        try {
            String line = reader.readLine();
            ArrayList<Movie> movies = new ArrayList<>();

            while (line != null) {
                String[] tokens = line.split("/");

                String title = tokens[0];
                String genre = String.valueOf(MovieGenre.valueOf(tokens[1]));
                String type = String.valueOf(MovieType.valueOf(tokens[2]));

                String rating = tokens[3];

                String s = tokens[4];
                SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dt.parse(s);
                Movie movie = new Movie(title, MovieGenre.valueOf(genre), MovieType.valueOf(type), rating, date);
                movies.add(movie);

                line = reader.readLine();
            }
            return movies;

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


