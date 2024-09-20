package org.medTechSolutions.platform.exams_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ExamsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamsServiceApplication.class, args);
	}

}
