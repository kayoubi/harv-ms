package edu.microservices.springboot.cnfsrv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CnfsrvApplication {

	public static void main(String[] args) {
		SpringApplication.run(CnfsrvApplication.class, args);
	}

}
