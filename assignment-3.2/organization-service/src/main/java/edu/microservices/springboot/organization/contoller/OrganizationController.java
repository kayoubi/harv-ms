package edu.microservices.springboot.organization.contoller;

import edu.microservices.springboot.organization.model.Organization;
import edu.microservices.springboot.organization.repository.OrganizationRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author khaled
 */
@RestController
public class OrganizationController {
    private final OrganizationRepository organizationRepository;

    public OrganizationController(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @GetMapping("/organizations/{id}")
    public Organization getById(@PathVariable String id) {
        return organizationRepository.findById(id).orElse(null);
    }

    @GetMapping("/organizations")
    public Iterable<Organization> getById() {
        return organizationRepository.findAll();
    }

    @DeleteMapping("/organizations/{id}")
    public void delete(@PathVariable String id) {
        organizationRepository.delete(organizationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id: " + id)));
    }

}
