package org.medTechSolutions.platform.exams_service.Exams.interfaces.rest.cucumber;

import io.cucumber.java.en.*;
import org.medTechSolutions.platform.exams_service.Exams.domain.model.aggregates.Exam;
import org.medTechSolutions.platform.exams_service.Exams.domain.model.commands.CreateExamCommand;
import org.medTechSolutions.platform.exams_service.Exams.domain.services.ExamCommandService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

public class ExamsSteps {


    private final ExamCommandService examCommandService;

    private CreateExamCommand createExamCommand;
    private Exam createdExam;

    public ExamsSteps(ExamCommandService examCommandService) {
        this.examCommandService = examCommandService;
    }

    @Given("the exam details are provided")
    public void the_exam_details_are_provided() {
        createExamCommand = new CreateExamCommand(
                1L,
                1L,
                "Blood Test",
                LocalDate.of(2023, 1, 1)
        );
    }

    @When("the user submits the exam request")
    public void the_user_submits_the_exam_request() {
        createdExam = examCommandService.handle(createExamCommand).orElse(null);
        if (createdExam != null) {
            createdExam.setExamResultsReady(false); // Set the default value for examResultsReady
            createdExam.setExamResultsUrl(""); // Set a default value for examResultsUrl
        }
    }

    @Then("the exam is created successfully")
    public void the_exam_is_created_successfully() {
        assertNotNull(createdExam);
    }
}
