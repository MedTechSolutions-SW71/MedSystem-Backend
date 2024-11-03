package org.medTechSolutions.platform.exams_service.Exams.interfaces.rest.transform;

import org.medTechSolutions.platform.exams_service.Exams.domain.model.commands.CreateExamCommand;
import org.medTechSolutions.platform.exams_service.Exams.interfaces.rest.resources.CreateExamResource;

public class CreateExamCommandFromResourceAssembler {
    public static CreateExamCommand toCommandFromResource(CreateExamResource resource) {
        return new CreateExamCommand(
                resource.doctorId(),
                resource.patientId(),
                resource.examType(),
                resource.examDate()
        );
    }
}
