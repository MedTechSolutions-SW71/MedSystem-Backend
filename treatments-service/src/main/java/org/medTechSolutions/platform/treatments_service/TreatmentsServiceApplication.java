package org.medTechSolutions.platform.treatments_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TreatmentsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TreatmentsServiceApplication.class, args);
	}

}