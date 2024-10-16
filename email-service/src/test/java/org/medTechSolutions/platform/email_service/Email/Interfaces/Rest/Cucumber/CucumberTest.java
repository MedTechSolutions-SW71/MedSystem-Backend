package org.medTechSolutions.platform.email_service.Email.Interfaces.Rest.Cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "org.medTechSolutions.platform.email_service.Email.Interfaces.rest.cucumber")
public class CucumberTest {
}