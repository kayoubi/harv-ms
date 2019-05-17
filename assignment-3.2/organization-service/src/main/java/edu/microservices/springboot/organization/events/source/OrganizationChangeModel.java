package edu.microservices.springboot.organization.events.source;

/**
 * @author khaled
 */
public class OrganizationChangeModel {
    private String typeName;
    private String action;
    private String orgId;
    private String correlationId;

    public OrganizationChangeModel(String typeName, String action, String orgId, String correlationId) {
        this.typeName = typeName;
        this.action = action;
        this.orgId = orgId;
        this.correlationId = correlationId;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getAction() {
        return action;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    @Override
    public String toString() {
        return "OrganizationChangeModel{" +
            "typeName='" + typeName + '\'' +
            ", action='" + action + '\'' +
            ", orgId='" + orgId + '\'' +
            ", correlationId='" + correlationId + '\'' +
            '}';
    }
}
