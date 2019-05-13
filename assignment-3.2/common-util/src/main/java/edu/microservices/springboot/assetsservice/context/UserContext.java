package edu.microservices.springboot.assetsservice.context;

/**
 * @author khaled
 */
import org.springframework.stereotype.Component;

@Component
public class UserContext {
    static final String CORRELATION_ID = "tmx-correlation-id";
    static final String AUTH_TOKEN     = "tmx-auth-token";
    static final String USER_ID        = "tmx-user-id";
    static final String ORG_ID         = "tmx-org-id";

    private String correlationId = "";
    private String authToken = "";
    private String userId = "";
    private String orgId = "";

    String getCorrelationId() {
        return correlationId;
    }

    void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    String getAuthToken() {
        return authToken;
    }

    void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    String getUserId() {
        return userId;
    }

    void setUserId(String userId) {
        this.userId = userId;
    }

    String getOrgId() {
        return orgId;
    }

    void setOrgId(String orgId) {
        this.orgId = orgId;
    }

}
