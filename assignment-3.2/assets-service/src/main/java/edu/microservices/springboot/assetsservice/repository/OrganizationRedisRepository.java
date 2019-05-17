package edu.microservices.springboot.assetsservice.repository;

import edu.microservices.springboot.assetsservice.domain.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * @author khaled
 */
@Repository
public class OrganizationRedisRepository {
    private static final String HASH_NAME ="organization";

    private RedisTemplate<String, Organization> redisTemplate;
    private HashOperations<String, String, Organization> hashOperations;

    @Autowired
    public OrganizationRedisRepository(RedisTemplate<String, Organization> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    public void saveOrganization(Organization org) {
        hashOperations.put(HASH_NAME, org.getId(), org);
    }

    public void updateOrganization(Organization org) {
        hashOperations.put(HASH_NAME, org.getId(), org);
    }

    public void deleteOrganization(String organizationId) {
        hashOperations.delete(HASH_NAME, organizationId);
    }

    public Organization findOrganization(String organizationId) {
        return hashOperations.get(HASH_NAME, organizationId);
    }
}
