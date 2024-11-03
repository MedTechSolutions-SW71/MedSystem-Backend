package org.medTechSolutions.platform.exams_service.Exams.interfaces.rest.resources;

import java.time.LocalDate;
import java.util.Date;

public record ExamResource(
        Long id,
        Long doctorId,
        Long patientId,
        String examType,
        LocalDate examDate,
        LocalDate examResultDate,
        Boolean examResult
) {
}
