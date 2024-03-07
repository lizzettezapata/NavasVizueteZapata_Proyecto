package com.app.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroserviciosBibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosBibliotecaApplication.class, args);
	}

}
