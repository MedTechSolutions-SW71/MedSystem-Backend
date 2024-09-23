package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "org.medTechSolutions.platform.profiles_service.User.Interfaces.rest")
public class CucumberTest {
}