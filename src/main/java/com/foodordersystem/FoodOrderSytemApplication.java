package com.foodordersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:63342")

public class FoodOrderSytemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodOrderSytemApplication.class, args);
	}

}
