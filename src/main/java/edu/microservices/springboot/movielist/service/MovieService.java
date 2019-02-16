package edu.microservices.springboot.movielist.service;

import edu.microservices.springboot.movielist.model.Movie;
import edu.microservices.springboot.movielist.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author khaled
 */
@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAll() {
        return StreamSupport
            .stream(movieRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }

    public Movie create(Movie movie) {
        return movieRepository.save(new Movie(movie.getTitle()));
    }
}
