package com.tantely.petrostationmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetroStationManagerApplication {

	private String name;
	public static void main(String[] args) {
		SpringApplication.run(PetroStationManagerApplication.class, args);
	}
}
