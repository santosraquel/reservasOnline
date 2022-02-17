package com.reservaonline;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReservaonlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservaonlineApplication.class, args);
		SpringApplication app = new SpringApplication(ReservaonlineApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8082"));
		app.run(args);
	}

}
