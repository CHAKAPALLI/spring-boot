package MovieBooking.Service;

import MovieBooking.Model.Movie;
import MovieBooking.Exceptions.MovieNotFoundException;
import MovieBooking.Exceptions.MovieAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final List<Movie> movies = new ArrayList<>();

    @Override
    public List<Movie> getAllMovies() {
        return movies;
    }

    @Override
    public Movie getMovieById(long id) {
        return movies.stream()
                .filter(movie -> movie.getId() == id)
                .findFirst()
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with ID: " + id));
    }

    @Override
    public Movie addMovie(Movie movie) {
        if (movies.stream().anyMatch(m -> m.getId() == movie.getId())) {
            throw new MovieAlreadyExistsException("Movie with ID " + movie.getId() + " already exists.");
        }
        movies.add(movie);
        return movie;
    }

    @Override
    public Movie updateMovie(long id, Movie updatedMovie) {
        Movie existingMovie = getMovieById(id);
        existingMovie.setMovieName(updatedMovie.getMovieName());
        existingMovie.setMovieDirector(updatedMovie.getMovieDirector());
        existingMovie.setMovieRating(updatedMovie.getMovieRating());
        existingMovie.setMovieLanguage(updatedMovie.getMovieLanguage());
        existingMovie.setWriters(updatedMovie.getWriters());
        existingMovie.setActors(updatedMovie.getActors());
        existingMovie.setGenre(updatedMovie.getGenre());
        return existingMovie;
    }

    @Override
    public void deleteMovie(long id) {
        Movie movieToDelete = getMovieById(id);
        movies.remove(movieToDelete);
    }
}
