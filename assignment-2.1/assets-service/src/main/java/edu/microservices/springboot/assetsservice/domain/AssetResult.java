package edu.microservices.springboot.assetsservice.domain;

import javax.persistence.Column;

/**
 * @author khaled
 */
public class AssetResult {
    private Long id;
    private String assetName;
    private String assetType;
    private Organization organization;

    public AssetResult(Long id, String assetName, String assetType, Organization organization) {
        this.id = id;
        this.assetName = assetName;
        this.assetType = assetType;
        this.organization = organization;
    }

    public Long getId() {
        return id;
    }

    public String getAssetName() {
        return assetName;
    }

    public String getAssetType() {
        return assetType;
    }

    public Organization getOrganization() {
        return organization;
    }
}
