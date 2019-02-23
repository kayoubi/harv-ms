package edu.microservices.springboot.movielist.model;

public class MovieBuilder {
    private String title;
    private int year;
    private String director;
    private String star;
    private String viewer;
    private String description;
    
    private MovieBuilder() {}

    public static MovieBuilder builder() {
        return new MovieBuilder();
    }
    
    public MovieBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public MovieBuilder withYear(int year) {
        this.year = year;
        return this;
    }

    public MovieBuilder withDirector(String director) {
        this.director = director;
        return this;
    }

    public MovieBuilder withStar(String star) {
        this.star = star;
        return this;
    }

    public MovieBuilder withViewer(String viewer) {
        this.viewer = viewer;
        return this;
    }

    public MovieBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public Movie createMovie() {
        return new Movie(title, year, director, star, viewer, description);
    }
}