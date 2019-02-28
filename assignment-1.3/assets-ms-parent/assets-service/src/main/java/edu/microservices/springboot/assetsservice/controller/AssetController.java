package edu.microservices.springboot.assetsservice.controller;

import edu.microservices.springboot.assetsservice.model.Asset;
import edu.microservices.springboot.assetsservice.service.AssetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author khaled
 */
@RequestMapping("v1/organizations/{organizationId}")
@RestController
public class AssetController {
    private final AssetService assetService;


    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/assets/{assetId}")
    public Asset getAsset(@PathVariable String organizationId, @PathVariable String assetId) {
        return assetService.getAsset(organizationId, assetId).orElse(null);
    }

    @PutMapping("/assets/{assetId}")
    public Asset updateAsset(@PathVariable String organizationId, @PathVariable String assetId, @RequestBody Asset asset) {
        return assetService.updateAsset(organizationId, assetId, asset);
    }

    @DeleteMapping("/assets/{assetId}")
    public void deleteAsset(@PathVariable String organizationId, @PathVariable String assetId) {
        assetService.deleteAsset(organizationId, assetId);
    }

    @PostMapping("/assets")
    public Asset createAssets(@PathVariable String organizationId, @RequestBody Asset asset) {
        asset.setOrganizationId(organizationId);
        return assetService.create(asset);
    }

    @GetMapping("/assets")
    public List<Asset> allAssets(@PathVariable String organizationId) {
        return assetService.allAssets(organizationId);
    }
}
