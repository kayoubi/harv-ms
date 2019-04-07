package edu.microservices.springboot.assetsservice.model;

import javax.persistence.*;

/**
 * @author khaled
 */
@Entity
@Table(name = "organizations")
public class Organization {
    @Id
    @Column(name = "organization_id")
    @GeneratedValue
    String id;
    @Column(nullable = false)
    String name;
    @Column(name = "contact_name", nullable = false)
    String contactName;
    @Column(name = "contact_email", nullable = false)
    String contactEmail;
    @Column(name = "contact_phone", nullable = false)
    String contactPhone;

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

    public String getName() {
        return name;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }
}
