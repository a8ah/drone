package com.demo.drone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
// @SpringBootApplication(scanBasePackages = {"com.demo.drone.data"})
// @EntityScan(basePackages = {"com.demo.drone.data"})
public class DroneApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroneApplication.class, args);
	}

}
