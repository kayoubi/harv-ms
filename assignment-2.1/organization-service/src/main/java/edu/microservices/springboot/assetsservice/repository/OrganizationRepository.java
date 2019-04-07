package edu.microservices.springboot.assetsservice.repository;

import edu.microservices.springboot.assetsservice.model.Organization;
import org.springframework.data.repository.CrudRepository;

/**
 * @author khaled
 */
public interface OrganizationRepository extends CrudRepository<Organization, String> {
}
