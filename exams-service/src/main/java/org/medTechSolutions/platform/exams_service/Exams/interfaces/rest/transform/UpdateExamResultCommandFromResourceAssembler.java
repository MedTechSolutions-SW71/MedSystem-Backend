package org.medTechSolutions.platform.exams_service.Exams.interfaces.rest.transform;

import org.medTechSolutions.platform.exams_service.Exams.domain.model.commands.UpdateExamCommand;
import org.medTechSolutions.platform.exams_service.Exams.interfaces.rest.resources.UpdateExamResultResource;

public class UpdateExamResultCommandFromResourceAssembler {
    public static UpdateExamCommand toCommandFromResource(Long examId,UpdateExamResultResource resource) {
        return new UpdateExamCommand(
                examId,
                resource.examResultsReady(),
                resource.examResultsUrl()
        );
    }
}
