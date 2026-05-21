package com.demo.surefiretest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SurefiretestdemoApplication - Main Application Class
 * 
 * This is the entry point of the Spring Boot application.
 * @SpringBootApplication enables:
 * - @Configuration: Marks this as a configuration class
 * - @EnableAutoConfiguration: Auto-configures Spring Boot components
 * - @ComponentScan: Scans for Spring components in this package
 * 
 * To run the application:
 * 1. mvn spring-boot:run
 * 2. java -jar target/academic-result-platform-0.0.1-SNAPSHOT.jar
 */
@SpringBootApplication
public class SurefiretestdemoApplication {

	public static void main(String[] args) {
		// SpringApplication.run() bootstraps the Spring application
		SpringApplication.run(SurefiretestdemoApplication.class, args);
	}

}
