package edu.microservices.springboot.assetsservice.service;

import edu.microservices.springboot.assetsservice.domain.AssetResult;
import edu.microservices.springboot.assetsservice.domain.Organization;
import edu.microservices.springboot.assetsservice.repository.AssetRepository;
import edu.microservices.springboot.assetsservice.model.Asset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author khaled
 */
@Service
public class AssetService {
    @Value("${spring.jpa.database}")
    private String db;

    private final AssetRepository repository;
    private final OrganizationService organizationService;
    private final OrganizationRestTemplateClient organizationRestTemplateClient;
    private final OrganizationFeignClient organizationFeignClient;

    public AssetService(AssetRepository repository, OrganizationService organizationService, OrganizationRestTemplateClient organizationRestTemplateClient, OrganizationFeignClient organizationFeignClient) {
        this.repository = repository;
        this.organizationService = organizationService;
        this.organizationRestTemplateClient = organizationRestTemplateClient;
        this.organizationFeignClient = organizationFeignClient;
    }

    public List<Asset> allAssets(String organizationId) {
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

    Organization getOrganization(String organizationId, String type) {
        Optional<Organization> organization;
        if ("discover".equals(type)) {
            organization = organizationRestTemplateClient.getOrganization(organizationId);
        } else if ("feign".equals(type)) {
            organization = Optional.of(organizationFeignClient.getOrganization(organizationId));
        } else {
            organization = organizationService.getOrganization(organizationId);
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
}
