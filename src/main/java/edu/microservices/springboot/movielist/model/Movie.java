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
    private int stars;
    private String viewer;
    private String description;

    public Movie() {
    }

    public Movie(String title, int year, String director, int stars, String viewer, String description) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.stars = stars;
        this.viewer = viewer;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
