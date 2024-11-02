package org.medTechSolutions.platform.exams_service.Exams.interfaces.rest.resources;

import java.util.Date;

public record ExamResource(
        Long id,
        Long doctorId,
        Long patientId,
        String examType,
        Date examDate,
        Date examResultDate,
        Boolean examResult
) {
}
