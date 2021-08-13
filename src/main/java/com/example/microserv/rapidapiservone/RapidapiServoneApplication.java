package com.example.microserv.rapidapiservone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient

public class RapidapiServoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(RapidapiServoneApplication.class, args);
	}

}
