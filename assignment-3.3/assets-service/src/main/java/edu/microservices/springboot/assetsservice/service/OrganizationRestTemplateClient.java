package edu.microservices.springboot.assetsservice.service;

import edu.microservices.springboot.assetsservice.context.UserContextHolder;
import edu.microservices.springboot.assetsservice.domain.Organization;
import edu.microservices.springboot.assetsservice.repository.OrganizationRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static edu.microservices.springboot.assetsservice.service.OrganizationDiscoveryService.ORGANIZATION_SERVICE;


/**
 * @author khaled
 */
@Service
public class OrganizationRestTemplateClient {
    private static final Logger logger = LoggerFactory.getLogger(OrganizationRestTemplateClient.class);

    private final RestTemplate restTemplate;
    private final OrganizationRedisRepository orgRedisRepo;

    public OrganizationRestTemplateClient(RestTemplate restTemplate, OrganizationRedisRepository orgRedisRepo) {
        this.restTemplate = restTemplate;
        this.orgRedisRepo = orgRedisRepo;
    }

    public Optional<Organization> getOrganization(String organizationId) {
        logger.debug("In Licensing Service.getOrganization: {}", UserContextHolder.getCorrelationId());

        Optional<Organization> org = checkRedisCache(organizationId);

        if (org.isPresent()) {
            logger.debug("I have successfully retrieved an organization {} from the redis cache: {}", organizationId, org);
            return org;
        }

        logger.debug("Unable to locate organization from the redis cache: {}.", organizationId);

        ResponseEntity<Organization> restExchange =
            restTemplate.exchange(
                "http://" + ORGANIZATION_SERVICE + "/organizations/{organizationId}",
                HttpMethod.GET,
                null,
                Organization.class,
                organizationId
            );

        org = Optional.ofNullable(restExchange.getBody());

        org.ifPresent(this::cacheOrganizationObject);

        return org;
    }


    private Optional<Organization> checkRedisCache(String organizationId) {
        try {
            return Optional.ofNullable(orgRedisRepo.findOrganization(organizationId));
        }
        catch (Exception ex){
            logger.error("Error encountered while trying to retrieve organization {} check Redis Cache.  Exception {}", organizationId, ex);
            return Optional.empty();
        }
    }

    private void cacheOrganizationObject(Organization org) {
        try {
            orgRedisRepo.saveOrganization(org);
        }catch (Exception ex){
            logger.error("Unable to cache organization {} in Redis. Exception {}", org.getId(), ex);
        }
    }
}
