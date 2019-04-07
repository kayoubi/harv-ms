package edu.microservices.springboot.assetsservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import edu.microservices.springboot.assetsservice.domain.AssetResult;
import edu.microservices.springboot.assetsservice.domain.Organization;
import edu.microservices.springboot.assetsservice.repository.AssetRepository;
import edu.microservices.springboot.assetsservice.model.Asset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * @author khaled
 */
@Service
public class AssetService {
    @Value("${spring.jpa.database}")
    private String db;

    private static final Logger logger = LoggerFactory.getLogger(AssetService.class);

    private final AssetRepository repository;
    private final OrganizationDiscoveryService organizationDiscoveryService;
    private final OrganizationRestTemplateClient organizationRestTemplateClient;
    private final OrganizationFeignClient organizationFeignClient;

    public AssetService(AssetRepository repository, OrganizationDiscoveryService organizationDiscoveryService, OrganizationRestTemplateClient organizationRestTemplateClient, OrganizationFeignClient organizationFeignClient) {
        this.repository = repository;
        this.organizationDiscoveryService = organizationDiscoveryService;
        this.organizationRestTemplateClient = organizationRestTemplateClient;
        this.organizationFeignClient = organizationFeignClient;
    }

    @HystrixCommand
    public List<Asset> allAssets(String organizationId) {
        randomlyRunLong();
        return repository.findAllByOrganizationId(organizationId);
    }

    public Asset create(Asset asset) {
        return repository.save(asset);
    }

    public Optional<AssetResult> getAsset(String organizationId, String assetId, String type) {
        Optional<Asset> asset = getAsset(organizationId, assetId);
        if (asset.isPresent()) {
            Asset a = asset.get();
            return Optional.of(new AssetResult(a.getId(), a.getAssetName(), a.getAssetType(), getOrganization(a.getOrganizationId(), type)));
        }
        return Optional.empty();
    }

    /**
     * find an asset with an Id in a specific Org
     * @param organizationId the org id to look in
     * @param assetId the Asset Id
     * @return an Optional Asset if found one
     */
    public Optional<Asset> getAsset(String organizationId, String assetId) {
        try {
            Long id = Long.parseLong(assetId);
            final Optional<Asset> asset = repository.findById(id);
            if (asset.isPresent()) {
                if (asset.get().getOrganizationId().equals(organizationId)) {
                    return asset;
                }
            }
        } catch (NumberFormatException nfe) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    private final Organization DUMMY = new Organization(null, "Dummy Org", null, null, null);

    @HystrixCommand
    Organization getOrganization(String organizationId, String type) {
        Optional<Organization> organization;
        if ("discovery".equals(type)) {
            logger.debug("Using the Discovery Service");
            organization = organizationDiscoveryService.getOrganization(organizationId);
        } else if ("feign".equals(type)) {
            logger.debug("Using the Feign Client");
            organization = Optional.of(organizationFeignClient.getOrganization(organizationId));
        } else {
            logger.debug("Using the Rest Template");
            organization = organizationRestTemplateClient.getOrganization(organizationId);
        }
        return organization.orElse(DUMMY);
    }

    /**
     * Delete an asset with an Id in a specific Org, do nothing if non found
     * @param organizationId the org id to look in
     * @param assetId the Asset Id to delete
     */
    public void deleteAsset(String organizationId, String assetId) {
        Optional<Asset> asset = getAsset(organizationId, assetId);
        asset.ifPresent(a -> {
            if (a.getOrganizationId().equals(organizationId)) {
                repository.delete(a);
            }
        });
    }

    /**
     * find an asset with an Id in a specific Org and update it based on the Asset passed
     * if no Asset found create a new one in the specified org based on the Asset param
     * @param organizationId the org id to look in
     * @param assetId the Asset Id to update
     * @param asset the Asset object to be used to update / create
     * @return an updated version if one found, otherwise the newly created Asset
     */
    public Asset updateAsset(String organizationId, String assetId, Asset asset) {
        Optional<Asset> existing = getAsset(organizationId, assetId);
        if (existing.isPresent()) {
            Asset a = existing.get();
            a.setOrganizationId(
                asset.getOrganizationId() != null ? asset.getOrganizationId() : organizationId
            );
            a.setAssetName(asset.getAssetName());
            a.setAssetType(asset.getAssetType());
            return repository.save(a);
        }
        // create a new Asset using the org ID in the request path
        asset.setOrganizationId(organizationId);
        return repository.save(asset);
    }

    private void randomlyRunLong(){
        Random rand = new Random();

        int randomNum = rand.nextInt((3 - 1) + 1) + 1;

        if (randomNum==3) sleep();
    }

    private void sleep(){
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
