package edu.microservices.springboot.assetsservice.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author khaled
 */
@Entity
@Table(name = "asset")
public class Asset {
    @Id
    @Column(name = "asset_id")
    @GeneratedValue
    private Long id;
    @Column(name = "organization_id", nullable = false)
    private String organizationId;
    @Column(name = "asset_name", nullable = false)
    private String assetName;
    @Column(name = "asset_type", nullable = false)
    private String assetType;

    public Asset() {
    }

    Asset(Long id, String organizationId, String assetName, String assetType) {
        this.id = id;
        this.organizationId = organizationId;
        this.assetName = assetName;
        this.assetType = assetType;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
