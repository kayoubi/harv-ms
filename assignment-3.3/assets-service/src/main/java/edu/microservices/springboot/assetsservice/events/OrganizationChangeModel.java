package edu.microservices.springboot.assetsservice.events;

/**
 * @author khaled
 */
public class OrganizationChangeModel {
    private final String typeName;
    private final String action;
    private final String orgId;
    private final String correlationId;

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
