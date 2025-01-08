package com.example.JpaBoard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@SpringBootApplication
@ConfigurationPropertiesScan
public class JpaBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaBoardApplication.class, args);
	}

}
