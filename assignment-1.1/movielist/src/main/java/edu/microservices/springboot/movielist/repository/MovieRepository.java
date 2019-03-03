package edu.microservices.springboot.movielist.repository;

import edu.microservices.springboot.movielist.model.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author khaled
 */
public interface MovieRepository extends CrudRepository<Movie, Long> {
    /**
     * a JPA method to return all movies by a particular viewer
     * @param viewer the viewer to return its movies
     * @return List of movies by the specified viewer
     */
    List<Movie> getAllByViewerOrderByIdDesc(String viewer);
}
