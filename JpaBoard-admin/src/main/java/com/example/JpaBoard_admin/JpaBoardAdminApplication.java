package com.example.JpaBoard_admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan //properties에있는걸 스캔하기 위해 꼭 사용(ThymeleafConfig에서  @ConfigurationProperties("spring.thymeleaf3") 이부분을 위해서 사용)
@SpringBootApplication
public class JpaBoardAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaBoardAdminApplication.class, args);
	}

}
