package edu.microservices.springboot.movielist.service;

import edu.microservices.springboot.movielist.model.Movie;
import edu.microservices.springboot.movielist.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * a Proxy Service class between the Controller and the DAO layer
 *
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

    public List<Movie> findByViewer(final String viewer) {
        return movieRepository.getAllByViewerOrderByIdDesc(viewer);
    }

    public Movie create(final Movie movie) {
        return movieRepository.save(movie);
    }
}
