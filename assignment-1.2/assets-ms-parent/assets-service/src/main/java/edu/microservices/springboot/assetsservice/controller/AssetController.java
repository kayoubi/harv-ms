package edu.microservices.springboot.assetsservice.controller;

import edu.microservices.springboot.assetsservice.model.Asset;
import edu.microservices.springboot.assetsservice.service.AssetService;
import org.springframework.web.bind.annotation.*;

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
        return assetService.getAsset(organizationId, assetId);
    }
}
