package ro.sci.cinema.domain;

import org.springframework.util.StringUtils;
import ro.sci.cinema.service.ValidationException;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Movie extends AbstractModel {
    private String title;
    private MovieGenre genre;
    private MovieType type;
    private String rating;


    public Movie(String title, MovieGenre genre, MovieType type, String rating) {
        this.title = title;
        this.genre = genre;
        this.type = type;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }

    public MovieType getType() {
        return type;
    }

    public void setType(MovieType type) {
        this.type = type;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre=" + genre +
                ", type=" + type +
                ", rating='" + rating + '\'' +
                '}';
    }

    private void validate(Movie movie) throws ValidationException{
        Date currentDate = new Date();
        List<String> errors = new LinkedList<String>();
        if (StringUtils.isEmpty(movie.getTitle())) {
            errors.add("Title is empty.");
        }
        if (movie.getGenre() == null) {
            errors.add("Genre is empty.");
        }
        if (movie.getType() == null) {
            errors.add("Type is empty.");
        }
        if (movie.getRating() == null) {
            errors.add("Rating is empty.");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors.toArray(new String[]{}));
        }
    }
}
