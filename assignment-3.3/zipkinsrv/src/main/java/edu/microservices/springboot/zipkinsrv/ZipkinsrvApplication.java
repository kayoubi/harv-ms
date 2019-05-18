package edu.microservices.springboot.zipkinsrv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class ZipkinsrvApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinsrvApplication.class, args);
	}

}
