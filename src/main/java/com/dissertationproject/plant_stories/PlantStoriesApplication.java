package com.dissertationproject.plant_stories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.dissertationproject.plant_stories.model")
public class PlantStoriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlantStoriesApplication.class, args);
	}

}
