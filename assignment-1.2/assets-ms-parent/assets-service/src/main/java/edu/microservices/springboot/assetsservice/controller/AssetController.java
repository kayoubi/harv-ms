package edu.microservices.springboot.assetsservice.controller;

import edu.microservices.springboot.assetsservice.model.Asset;
import edu.microservices.springboot.assetsservice.service.AssetService;
import org.springframework.web.bind.annotation.*;

/**
 * a REST Controller to handle Http requests and return serialized JSON response
 *
 * @author khaled
 */
@RequestMapping("v1/organizations/{organizationId}")
@RestController
public class AssetController {
    private final AssetService assetService;


    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    /**
     * handles request "/v1/organizations/{organizationId}/assets/{assetId} to return an Asset
     * by org-id and asset-id
     * @param organizationId the org id
     * @param assetId the asset id
     * @return corresponding Asset object
     */
    @GetMapping("/assets/{assetId}")
    public Asset getAsset(@PathVariable String organizationId, @PathVariable String assetId) {
        return assetService.getAsset(organizationId, assetId);
    }
}
