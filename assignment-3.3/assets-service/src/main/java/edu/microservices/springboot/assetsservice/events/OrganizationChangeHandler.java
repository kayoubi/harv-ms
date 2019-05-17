package edu.microservices.springboot.assetsservice.events;

import edu.microservices.springboot.assetsservice.repository.OrganizationRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @author khaled
 */
@EnableBinding(Sink.class)
public class OrganizationChangeHandler {
    private static final Logger logger = LoggerFactory.getLogger(OrganizationChangeHandler.class);

    private final OrganizationRedisRepository organizationRedisRepository;

    public OrganizationChangeHandler(OrganizationRedisRepository organizationRedisRepository) {
        this.organizationRedisRepository = organizationRedisRepository;
    }

    @StreamListener(Sink.INPUT)
    public void loggerSink(OrganizationChangeModel orgChange) {
        logger.debug("Received an event for organization {}", orgChange);

        switch(orgChange.getAction()){
            case "GET":
                logger.debug("Received a GET event from the organization service for organization id {}", orgChange.getOrgId());
                break;
            case "SAVE":
                logger.debug("Received a SAVE event from the organization service for organization id {}", orgChange.getOrgId());
                break;
            case "UPDATE":
                logger.debug("Received a UPDATE event from the organization service for organization id {}", orgChange.getOrgId());
                organizationRedisRepository.deleteOrganization(orgChange.getOrgId());
                break;
            case "DELETE":
                logger.debug("Received a DELETE event from the organization service for organization id {}", orgChange.getOrgId());
                organizationRedisRepository.deleteOrganization(orgChange.getOrgId());
                break;
            default:
                logger.error("Received an UNKNOWN event from the organization service of type {}", orgChange.getAction());
                break;

        }
    }
}
