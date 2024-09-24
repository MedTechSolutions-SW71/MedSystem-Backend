package org.medTechSolutions.platform.treatments_service.domain.model.commands;

public record AddExamResultToTreatmentCommand(
        Long treatmentId,
        Long examResultId
) {
}