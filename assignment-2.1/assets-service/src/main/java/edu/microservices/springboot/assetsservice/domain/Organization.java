package edu.microservices.springboot.assetsservice.domain;

/**
 * @author khaled
 */
public class Organization {
    private String id;
    private String name;
    private String contactName;
    private String contactEmail;
    private String contactPhone;

    public Organization() {
    }

    public Organization(String id, String name, String contactName, String contactEmail, String contactPhone) {
        this.id = id;
        this.name = name;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
