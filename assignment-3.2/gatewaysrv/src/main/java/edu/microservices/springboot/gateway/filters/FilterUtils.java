package edu.microservices.springboot.gateway.filters;

import com.netflix.zuul.context.RequestContext;

/**
 * @author khaled
 */
public class FilterUtils {
    static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN     = "tmx-auth-token";
    public static final String USER_ID        = "tmx-user-id";
    public static final String ORG_ID         = "tmx-org-id";
    static final String PRE_FILTER_TYPE = "pre";
    static final String POST_FILTER_TYPE = "post";
    static final String ROUTE_FILTER_TYPE = "route";

    static String getCorrelationId() {
        RequestContext ctx = RequestContext.getCurrentContext();
        if (ctx.getRequest().getHeader(CORRELATION_ID) != null) {
            return ctx.getRequest().getHeader(CORRELATION_ID);
        }
        return  ctx.getZuulRequestHeaders().get(CORRELATION_ID);
    }

    static void setCorrelationId(String correlationId) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(CORRELATION_ID, correlationId);
    }

    static String getServiceId() {
        RequestContext ctx = RequestContext.getCurrentContext();

        //We might not have a service id if we are using a static, non-eureka route.
        Object serviceId = ctx.get("serviceId");
        return serviceId == null ? "" : serviceId.toString();
    }

    static String getAuthToken(){
        RequestContext ctx = RequestContext.getCurrentContext();
        return ctx.getRequest().getHeader(AUTH_TOKEN);
    }

    static void setUserId(String userId){
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(USER_ID,  userId);
    }

    static void setOrgId(String orgId){
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(ORG_ID,  orgId);
    }
}
