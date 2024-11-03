package org.medTechSolutions.platform.exams_service.Exams.interfaces.rest.transform;

import org.medTechSolutions.platform.exams_service.Exams.domain.model.aggregates.Exam;
import org.medTechSolutions.platform.exams_service.Exams.interfaces.rest.resources.ExamResource;

public class ExamResourceFromEntityAssembler {
    public static ExamResource toResourceFromEntity(Exam exam) {
        return new ExamResource(
                exam.getId(),
                exam.getDoctorId(),
                exam.getPatientId(),
                exam.getExamType(),
                exam.getExamDate(),
                exam.getExamResultDate(),
                exam.getExamResultsReady(),
                exam.getExamResultsUrl()
        );
    }
}
