package edu.microservices.springboot.movielist.controller;

import edu.microservices.springboot.movielist.model.Movie;
import edu.microservices.springboot.movielist.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Controller class to accept HTTP request and response with a path to thymeleaf template
 *
 * @author khaled
 */
@Controller
public class MovieController {
    private final MovieService movieService;
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    private static final List<Integer> years = IntStream.range(1900, 2020).boxed().collect(Collectors.toList());

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * Handles access to the main page
     *
     */
    @GetMapping("/")
    public String home() {
        return "index";
    }

    /**
     * Handles access to the movie-list page
     * @param viewer request param, if passed will return all movies reviewed by that user
     *               otherwise empty list
     * @return movie-list thymeleaf page
     */
    @GetMapping("/movieList")
    public String movieList(@RequestParam(required = false) String viewer, Model model){
        if (viewer != null) {
            List<Movie> movieList = movieService.findByViewer(viewer);
            model.addAttribute("count", movieList.size());
            model.addAttribute("movies", movieList);
        }
        model.addAttribute("years", years);
        model.addAttribute("movie", new Movie());

        return "movieList";
    }

    /**
     * Handles accidental refresh by the user after submitting the form
     * @return the movie-list thymeleaf page
     */
    @GetMapping("/createMovie")
    public String createRedirect() {
        return "redirect:/movieList";
    }

    /**
     * Handles submitting the form if there is an validation error, keep the user on the same
     * page, otherwise create a movie and redirect to movie-list page for that user
     * @param movie the new Movie being submitted
     * @param bindingResult to check validation error
     * @return the next page the user will see
     */
    @PostMapping("/createMovie")
    public String create(Model model, @Valid Movie movie, BindingResult bindingResult) {
        model.addAttribute("years", years);
        if (bindingResult.hasErrors()) {
            return "movieList";
        }
        movieService.create(movie);

        return "redirect:/movieList?viewer=" + movie.getViewer();
    }
}
