package edu.microservices.springboot.assetsservice.service;

import edu.microservices.springboot.assetsservice.model.Asset;
import edu.microservices.springboot.assetsservice.model.AssetBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author khaled
 */
@Service
public class AssetService {
    @Value("${spring.jpa.database}")
    private String db;

    public Asset getAsset(String organizationId, String assetId) {
        return AssetBuilder.instance()
            .withId(assetId)
            .withAssetName("new Asset")
            .withAssetType(db)
            .withOrganizationId(organizationId)
            .build();
    }
}
