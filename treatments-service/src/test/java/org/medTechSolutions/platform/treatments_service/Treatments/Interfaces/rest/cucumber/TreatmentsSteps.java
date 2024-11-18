package org.medTechSolutions.platform.treatments_service.Treatments.Interfaces.rest.cucumber;

import io.cucumber.java.en.*;
import org.medTechSolutions.platform.treatments_service.domain.model.aggregates.Treatment;
import org.medTechSolutions.platform.treatments_service.domain.model.commands.CreateTreatmentCommand;
import org.medTechSolutions.platform.treatments_service.domain.services.TreatmentCommandService;

import static org.junit.Assert.assertNotNull;

public class TreatmentsSteps {


    private final TreatmentCommandService treatmentCommandService;

    private CreateTreatmentCommand createTreatmentCommand;
    private Treatment createdTreatment;

    public TreatmentsSteps(TreatmentCommandService treatmentCommandService) {
        this.treatmentCommandService = treatmentCommandService;
    }

    @Given("the treatment details are provided")
    public void the_treatment_details_are_provided() {
        createTreatmentCommand = new CreateTreatmentCommand(
                "Chemotherapy",
                "A treatment for cancer",
                "2023-01-01",
                "2023-12-31",
                1L,
                1L
        );
    }

    @When("the user submits the treatment request")
    public void the_user_submits_the_treatment_request() {
        createdTreatment = treatmentCommandService.handle(createTreatmentCommand).orElse(null);
    }

    @Then("the treatment is created successfully")
    public void the_treatment_is_created_successfully() {
        assertNotNull(createdTreatment);
    }
}