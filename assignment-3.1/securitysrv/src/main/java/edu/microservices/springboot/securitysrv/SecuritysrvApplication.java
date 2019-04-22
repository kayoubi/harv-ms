package edu.microservices.springboot.securitysrv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


// https://www.baeldung.com/spring-security-oauth2-enable-resource-server-vs-enable-oauth2-sso
@SpringBootApplication
@RestController
@EnableResourceServer//this was needed to this server acts as resource and can access "/user" using the token 
public class SecuritysrvApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuritysrvApplication.class, args);
	}

	@RequestMapping(value = { "/user" }, produces = "application/json")
	public Map<String, Object> user(OAuth2Authentication user) {

		Map<String, Object> userInfo = new HashMap<>();
		userInfo.put("user", user.getUserAuthentication().getPrincipal());
		userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));

		return userInfo;

	}
}
