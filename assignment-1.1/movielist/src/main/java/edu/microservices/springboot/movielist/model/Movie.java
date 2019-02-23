package edu.microservices.springboot.movielist.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author khaled
 */
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    private int year;
    @NotBlank
    private String director;
    @NotBlank
    private String star;
    @NotBlank
    private String viewer;
    @NotBlank
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

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getViewer() {
        return viewer;
    }

    public void setViewer(String viewer) {
        this.viewer = viewer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
