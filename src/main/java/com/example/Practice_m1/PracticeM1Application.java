package com.example.Practice_m1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class PracticeM1Application {

	public static void main(String[] args) {
		SpringApplication.run(PracticeM1Application.class, args);
	}

}
