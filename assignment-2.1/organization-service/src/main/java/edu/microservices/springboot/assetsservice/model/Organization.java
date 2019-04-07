package edu.microservices.springboot.assetsservice.model;

/**
 * @author khaled
 */
public class Organization {
    final String id;
    final String name;

    public Organization(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
