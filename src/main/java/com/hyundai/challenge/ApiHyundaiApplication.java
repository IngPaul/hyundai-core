package com.hyundai.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication()
@EnableCaching
public class ApiHyundaiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiHyundaiApplication.class, args);
	}
}
