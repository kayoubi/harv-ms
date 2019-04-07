package edu.microservices.springboot.assetsservice.service;

import edu.microservices.springboot.assetsservice.domain.Organization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author khaled
 */
@FeignClient("organization")
public interface OrganizationFeignClient {
    @GetMapping(value="/organizations/{organizationId}", consumes="application/json")
    Organization getOrganization(@PathVariable("organizationId") String organizationId);
}
