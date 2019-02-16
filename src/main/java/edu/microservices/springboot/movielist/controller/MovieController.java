package edu.microservices.springboot.movielist.controller;

import edu.microservices.springboot.movielist.model.Movie;
import edu.microservices.springboot.movielist.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

/**
 * @author khaled
 */
@Controller
public class MovieController {
    private final MovieService movieService;
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    public String home(){
        logger.info("root request");
        logger.info("created {}", movieService.create(new Movie(null, "foobar" + new Random().nextFloat())));
        logger.info("all {}", movieService.getAll().size() );
        return "index.html";
    }
}
