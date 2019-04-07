package edu.microservices.springboot.assetsservice.model;

public class AssetBuilder {
    private Long id;
    private String organizationId;
    private String assetName;
    private String assetType;
    
    private AssetBuilder() {}
    
    public static AssetBuilder instance() {
        return new AssetBuilder();
    }

    public AssetBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public AssetBuilder withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public AssetBuilder withAssetName(String assetName) {
        this.assetName = assetName;
        return this;
    }

    public AssetBuilder withAssetType(String assetType) {
        this.assetType = assetType;
        return this;
    }

    public Asset build() {
        return new Asset(id, organizationId, assetName, assetType);
    }
}