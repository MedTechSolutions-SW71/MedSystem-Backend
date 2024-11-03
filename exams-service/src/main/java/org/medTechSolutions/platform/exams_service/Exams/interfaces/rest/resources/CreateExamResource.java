package org.medTechSolutions.platform.exams_service.Exams.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public record CreateExamResource(
        @NotNull(message = "Doctor ID is required")
        Long doctorId,
        @NotNull(message = "Patient ID is required")
        Long patientId,
        @NotNull(message = "Exam type is required")
        String examType,
        @NotNull(message = "Exam date is required")
        LocalDate examDate
) {
}
