package org.medTechSolutions.platform.appointments_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AppointmentsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentsServiceApplication.class, args);
	}

}
