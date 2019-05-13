package edu.microservices.springboot.assetsservice.context;

import org.springframework.util.Assert;

/**
 * @author khaled
 */
public class UserContextHolder {
    private static final ThreadLocal<UserContext> userContext = new ThreadLocal<>();

    public static UserContext getContext(){
        UserContext context = userContext.get();

        if (context == null) {
            context = createEmptyContext();
            userContext.set(context);

        }
        return userContext.get();
    }

    public static String getCorrelationId() {
        return getContext().getCorrelationId();
    }

    public static void setContext(UserContext context) {
        Assert.notNull(context, "Only non-null UserContext instances are permitted");
        userContext.set(context);
    }

    private static UserContext createEmptyContext(){
        return new UserContext();
    }
}
