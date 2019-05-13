package edu.microservices.springboot.assetsservice.repository;

import edu.microservices.springboot.assetsservice.model.Asset;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author khaled
 */
public interface AssetRepository extends CrudRepository<Asset, Long> {
    /**
     * helper DAO method to find Assets in an organization
     * @param organizationId the org id to look in
     * @return List of Assets in the specified organization
     */
    List<Asset> findAllByOrganizationId(String organizationId);
}
