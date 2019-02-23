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

    @GetMapping("/")
    public String home() {
        return "index";
    }

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

    @GetMapping("/createMovie")
    public String createRedirect() {
        return "redirect:/movieList";
    }

    @PostMapping("/createMovie")
    public String create(Model model, @Valid Movie movie, BindingResult bindingResult) {
        model.addAttribute("years", years);
        if (bindingResult.hasErrors()) {

            return "/movieList";
        }
        movieService.create(movie);

        return "redirect:/movieList?viewer=" + movie.getViewer();
    }
}
