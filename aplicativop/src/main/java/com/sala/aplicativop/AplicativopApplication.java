package com.sala.aplicativop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AplicativopApplication {

	public static void main(String[] args) {
		SpringApplication.run(AplicativopApplication.class, args);
	}

}
