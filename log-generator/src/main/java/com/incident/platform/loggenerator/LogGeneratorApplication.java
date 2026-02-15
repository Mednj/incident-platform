package com.incident.platform.loggenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogGeneratorApplication.class, args);
		System.out.println("Hello project");
	}

}
