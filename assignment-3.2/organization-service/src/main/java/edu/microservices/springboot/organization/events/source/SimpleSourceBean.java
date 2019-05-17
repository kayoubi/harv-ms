package edu.microservices.springboot.organization.events.source;

import edu.microservices.springboot.assetsservice.context.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author khaled
 */
@Component
public class SimpleSourceBean {
    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

    private Source source;

    @Autowired
    public SimpleSourceBean(Source source){
        this.source = source;
    }

    public void publishOrgChange(String action, String orgId){
        logger.debug("Sending Kafka message {} for Organization Id: {}", action, orgId);

        OrganizationChangeModel change = new OrganizationChangeModel(
            OrganizationChangeModel.class.getTypeName(),
            action,
            orgId,
            UserContextHolder.getCorrelationId()
        );

        source
            .output()
            .send(MessageBuilder.withPayload(change).build());
    }
}
