package org.medTechSolutions.platform.exams_service.Exams.interfaces.rest.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "org.medTechSolutions.platform.exams_service.Exams.interfaces.rest.cucumber")
public class CucumberTest {
}
