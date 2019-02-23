package edu.microservices.springboot.movielist.controller;

import edu.microservices.springboot.movielist.model.Movie;
import edu.microservices.springboot.movielist.model.MovieBuilder;
import edu.microservices.springboot.movielist.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String home() {
        return "index";
    }

    @GetMapping("/movieList")
    public String movieList(Model model){
//        logger.info("created {}", movieService.create(MovieBuilder
//            .builder()
//            .withTitle("foobar " + new Random().nextInt())
//            .createMovie()).getId()
//        );

        model.addAttribute("count", movieService.getAll().size());
        model.addAttribute("movies", movieService.getAll());
        model.addAttribute("movie", new Movie());

        return "movieList";
    }

    @PostMapping("/createMovie")
    public String create(@ModelAttribute Movie movie, Model model) {
        movieService.create(movie);

        return "redirect:/movieList";
    }
}
