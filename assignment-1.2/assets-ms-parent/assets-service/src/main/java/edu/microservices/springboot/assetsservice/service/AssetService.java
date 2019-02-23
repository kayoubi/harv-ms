package edu.microservices.springboot.assetsservice.service;

import edu.microservices.springboot.assetsservice.model.Asset;
import edu.microservices.springboot.assetsservice.model.AssetBuilder;
import org.springframework.stereotype.Service;

/**
 * @author khaled
 */
@Service
public class AssetService {
    public Asset getAsset(String organizationId, String assetId) {
        return AssetBuilder.instance()
            .withId(assetId)
            .withAssetName("new Asset")
            .withAssetType("random type")
            .withOrganizationId(organizationId)
            .build();
    }
}
