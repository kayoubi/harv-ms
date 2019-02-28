package edu.microservices.springboot.assetsservice.repository;

import edu.microservices.springboot.assetsservice.model.Asset;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author khaled
 */
public interface AssetRepository extends CrudRepository<Asset, Long> {
    List<Asset> findAllByOrganizationId(String organizationId);
}
