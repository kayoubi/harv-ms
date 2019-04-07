package edu.microservices.springboot.assetsservice.controller;

import edu.microservices.springboot.assetsservice.context.UserContextHolder;
import edu.microservices.springboot.assetsservice.domain.AssetResult;
import edu.microservices.springboot.assetsservice.model.Asset;
import edu.microservices.springboot.assetsservice.service.AssetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author khaled
 */
@RequestMapping("v1/organizations/{organizationId}")
@RestController
public class AssetController {
    private static final Logger logger = LoggerFactory.getLogger(AssetController.class);

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    /**
     * REST API to retrieve an Asset
     * @param organizationId the org id to look in
     * @param assetId the Asset Id
     * @return the Asset if found one otherwise null
     */
    @GetMapping("/assets/{assetId}/{type}")
    public AssetResult getAsset(@PathVariable String organizationId, @PathVariable String assetId, @PathVariable String type) {
        return assetService.getAsset(organizationId, assetId, type).orElse(null);
    }

    /**
     * REST API to update / create an Asset
     * @param organizationId the org id to look in
     * @param assetId the Asset Id to update
     * @param asset Asset object that has the specified attributes
     * @return an updated version of the Asset if found, otherwise the newly created Asset
     */
    @PutMapping("/assets/{assetId}")
    public Asset updateAsset(@PathVariable String organizationId, @PathVariable String assetId, @RequestBody Asset asset) {
        return assetService.updateAsset(organizationId, assetId, asset);
    }

    /**
     * REST API to delete a nasset
     * @param organizationId the org id to look in
     * @param assetId the Asset Id to delete
     */
    @DeleteMapping("/assets/{assetId}")
    public void deleteAsset(@PathVariable String organizationId, @PathVariable String assetId) {
        assetService.deleteAsset(organizationId, assetId);
    }

    /**
     * REST API to create a new Asset
     * @param organizationId the org id to look in
     * @param asset the request body which has the Asset attributes
     * @return the newly created Asset
     */
    @PostMapping("/assets")
    public Asset createAssets(@PathVariable String organizationId, @RequestBody Asset asset) {
        asset.setOrganizationId(organizationId);
        return assetService.create(asset);
    }

    /**
     * REST API to get all Assets in an organization
     * @param organizationId the org id to look in
     * @return List of Assets belong to the organization
     */
    @GetMapping("/assets")
    public List<Asset> allAssets(@PathVariable String organizationId) {
        logger.debug("Got Correlation Id {}", UserContextHolder.getCorrelationId());
        return assetService.allAssets(organizationId);
    }
}
