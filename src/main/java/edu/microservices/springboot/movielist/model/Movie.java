package edu.microservices.springboot.movielist.model;

import javax.persistence.*;

/**
 * @author khaled
 */
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String title;
    private int year;
    private String director;
    private String star;
    private String viewer;
    private String description;

    public Movie() {
    }

    public Movie(String title, int year, String director, String star, String viewer, String description) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.star = star;
        this.viewer = viewer;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public String getStar() {
        return star;
    }

    public String getViewer() {
        return viewer;
    }

    public String getDescription() {
        return description;
    }
}
