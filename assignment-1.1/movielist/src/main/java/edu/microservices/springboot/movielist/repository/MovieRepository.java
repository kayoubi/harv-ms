package edu.microservices.springboot.movielist.repository;

import edu.microservices.springboot.movielist.model.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author khaled
 */
public interface MovieRepository extends CrudRepository<Movie, Long> {
    List<Movie> getAllByViewerOrderByIdDesc(String viewer);
}
