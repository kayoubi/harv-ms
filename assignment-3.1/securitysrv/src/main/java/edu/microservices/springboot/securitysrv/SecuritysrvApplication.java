package edu.microservices.springboot.securitysrv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// https://www.baeldung.com/spring-security-oauth2-enable-resource-server-vs-enable-oauth2-sso
@SpringBootApplication
public class SecuritysrvApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuritysrvApplication.class, args);
	}

}
