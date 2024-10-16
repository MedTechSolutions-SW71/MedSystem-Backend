package org.medTechSolutions.platform.appointments_service.Appointment.Interfaces.rest.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "org.medTechSolutions.platform.appointments_service.Appointment.Interfaces.rest.cucumber")
public class CucumberTest {
}