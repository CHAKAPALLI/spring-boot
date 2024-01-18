package MovieBooking.Controller;

import MovieBooking.Model.Movie;
import MovieBooking.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ticket")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/movie/{id}")
    public Movie getMovieById(@PathVariable long id) {
        return movieService.getMovieById(id);
    }

    @PostMapping("/movie")
    public Movie addMovie(@Valid @RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @PutMapping("/update/{id}")
    public Movie updateMovie(@PathVariable long id, @Valid @RequestBody Movie movie) {
        return movieService.updateMovie(id, movie);
    }

    @DeleteMapping("/movie/{id}")
    public void deleteMovie(@PathVariable long id) {
        movieService.deleteMovie(id);
    }
}
