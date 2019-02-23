package edu.microservices.springboot.assetsservice.model;

/**
 * @author khaled
 */
public class Asset {
    private String id;
    private String organizationId;
    private String assetName;
    private String assetType;

    Asset(String id, String organizationId, String assetName, String assetType) {
        this.id = id;
        this.organizationId = organizationId;
        this.assetName = assetName;
        this.assetType = assetType;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }
}
