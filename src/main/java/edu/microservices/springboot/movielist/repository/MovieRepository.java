package edu.microservices.springboot.movielist.repository;

import edu.microservices.springboot.movielist.model.Movie;
import org.springframework.data.repository.CrudRepository;

/**
 * @author khaled
 */
public interface MovieRepository extends CrudRepository<Movie, Long> {
}
