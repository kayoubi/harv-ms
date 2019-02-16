package edu.microservices.springboot.movielist.service;

import edu.microservices.springboot.movielist.domain.MovieEntity;
import edu.microservices.springboot.movielist.model.Movie;
import edu.microservices.springboot.movielist.repository.movieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author khaled
 */
@Service
public class MovieService {
    private final movieRepository movieRepository;

    public MovieService(movieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAll() {
        return StreamSupport
            .stream(movieRepository.findAll().spliterator(), false)
            .map(e -> new Movie(e.getId(), e.getTitle()))
            .collect(Collectors.toList());
    }

    public Movie create(Movie movie) {
        MovieEntity newMovie = movieRepository.save(new MovieEntity(movie.getName()));
        return new Movie(newMovie.getId(), newMovie.getTitle());
    }
}
