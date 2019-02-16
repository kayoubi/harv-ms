package edu.microservices.springboot.movielist.repository;

import edu.microservices.springboot.movielist.domain.MovieEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author khaled
 */
public interface movieRepository extends CrudRepository<MovieEntity, Long> {
}
