package edu.microservices.springboot.organization.repository;

import edu.microservices.springboot.organization.model.Organization;
import org.springframework.data.repository.CrudRepository;

/**
 * @author khaled
 */
public interface OrganizationRepository extends CrudRepository<Organization, String> {
}
