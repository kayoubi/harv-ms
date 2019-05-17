package edu.microservices.springboot.assetsservice.service;

import edu.microservices.springboot.assetsservice.domain.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

/**
 * @author khaled
 */
@Service
public class OrganizationDiscoveryService {
    private static final Logger logger = LoggerFactory.getLogger(OrganizationDiscoveryService.class);

    private final DiscoveryClient discoveryClient;
    static final String ORGANIZATION_SERVICE = "organization";
//    static final String ORGANIZATION_SERVICE = "localhost:8181";

    public OrganizationDiscoveryService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    public Optional<Organization> getOrganization(String organizationId) {
        List<ServiceInstance> instances = discoveryClient.getInstances(ORGANIZATION_SERVICE);
        if (instances.size() == 0)
            return Optional.empty();

        String serviceUri = String.format("%s/organizations/%s",
            instances.get(0).getUri().toString(),
            organizationId);

        logger.debug("Using the Organization Service URL {}", serviceUri);

        ResponseEntity<Organization> restExchange = new RestTemplate()
            .exchange(serviceUri, HttpMethod.GET, null, Organization.class, organizationId);

        return Optional.ofNullable(restExchange.getBody());
    }
}
