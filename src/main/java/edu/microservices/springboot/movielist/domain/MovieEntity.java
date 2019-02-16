package edu.microservices.springboot.movielist.domain;

import javax.persistence.*;

/**
 * @author khaled
 */
@Entity
@Table(name = "movie")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String title;

    public MovieEntity() {
    }

    public MovieEntity(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
