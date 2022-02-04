package com.benchraid.benchraid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.persistence.Entity;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.benchraid.benchraid.entities"})
public class BenchraidApplication {

	public static void main(String[] args) {
		SpringApplication.run(BenchraidApplication.class, args);
	}

}
