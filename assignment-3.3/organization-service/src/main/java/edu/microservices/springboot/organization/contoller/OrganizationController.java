package edu.microservices.springboot.organization.contoller;

import edu.microservices.springboot.organization.events.source.SimpleSourceBean;
import edu.microservices.springboot.organization.model.Organization;
import edu.microservices.springboot.organization.repository.OrganizationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author khaled
 */
@RestController
public class OrganizationController {
    private final OrganizationRepository organizationRepository;
    private final SimpleSourceBean sourceBean;

    public OrganizationController(OrganizationRepository organizationRepository, SimpleSourceBean sourceBean) {
        this.organizationRepository = organizationRepository;
        this.sourceBean = sourceBean;
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
        sourceBean.publishOrgChange("DELETE", id);
        organizationRepository.delete(organizationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id: " + id)));
    }

    @PutMapping("/organizations/{id}")
    public void update(@PathVariable String id, @RequestBody Organization organization) {
        sourceBean.publishOrgChange("UPDATE", id);
        organizationRepository.findById(id).ifPresent(o -> organizationRepository.save(organization));
    }


    @PostMapping("/organizations")
    public Organization create(@RequestBody Organization organization) {
        Organization saved = organizationRepository.save(organization);
        sourceBean.publishOrgChange("SAVE", saved.getId());
        return saved;
    }

}
