package edu.microservices.springboot.assetsservice;

import edu.microservices.springboot.assetsservice.domain.Organization;
import edu.microservices.springboot.assetsservice.events.OrganizationChangeModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableResourceServer // so the asset server requires auth
@EnableOAuth2Client // this is needed to provide OAuth2ClientContext below
@EnableBinding(Sink.class)
public class AssetsServiceApplication {
	private static final Logger logger = LoggerFactory.getLogger(AssetsServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AssetsServiceApplication.class, args);
	}

	@Value("${redis.server}")
	private String redisServer="";

	@Value("${redis.port}")
	private String redisPort="";

//	@LoadBalanced
//	@Bean
//	public RestTemplate getRestTemplate() {
//		return new RestTemplate();
//	}

	@Bean // this was needed to pass the access_token along to the other service (try to remove again and double check)
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

	@LoadBalanced // this is necessary to call other service using REST and its logical name e.g. http://organization/...
	@Bean
	public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext, OAuth2ProtectedResourceDetails details) {
		return new OAuth2RestTemplate(details, oauth2ClientContext);
	}

	@Bean// had to add this to make the injection of OAuth2ProtectedResourceDetails above happy
	public OAuth2ProtectedResourceDetails resource() {
		return new AuthorizationCodeResourceDetails();
	}

	@StreamListener(Sink.INPUT)
	public void loggerSink(OrganizationChangeModel orgChange) {
		logger.debug("Received an event for organization {}", orgChange);
	}

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
		configuration.setHostName(redisServer);
		configuration.setPort(Integer.valueOf(redisPort));

		return new JedisConnectionFactory(configuration);
	}

	@Bean
	public RedisTemplate<String, Organization> redisTemplate() {
		RedisTemplate<String, Organization> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
}
