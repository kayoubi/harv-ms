package edu.microservices.springboot.assetsservice.contoller;

import edu.microservices.springboot.assetsservice.model.Organization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author khaled
 */
@RestController
public class OrganizationController {
    @GetMapping("/organizations/{id}")
    public Organization getById(@PathVariable String id) {
        return new Organization(id, "Not too smart: " + id);
    }

}
