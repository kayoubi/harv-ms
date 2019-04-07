package edu.microservices.springboot.assetsservice.service;

import edu.microservices.springboot.assetsservice.domain.Organization;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static edu.microservices.springboot.assetsservice.service.OrganizationService.ORGANIZATION_SERVICE;


/**
 * @author khaled
 */
@Service
public class OrganizationRestTemplateClient {
    private final RestTemplate restTemplate;

    public OrganizationRestTemplateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<Organization> getOrganization(String organizationId) {
        ResponseEntity<Organization> restExchange =
            restTemplate.exchange(
                "http://" + ORGANIZATION_SERVICE + "/organizations/{organizationId}",
                HttpMethod.GET,
                null,
                Organization.class,
                organizationId
            );
        return Optional.ofNullable(restExchange.getBody());
    }
}
