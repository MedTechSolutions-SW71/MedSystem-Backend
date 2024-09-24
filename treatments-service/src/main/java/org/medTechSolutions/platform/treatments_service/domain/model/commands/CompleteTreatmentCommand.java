package org.medTechSolutions.platform.treatments_service.domain.model.commands;

public record CompleteTreatmentCommand(
        Long treatmentId // El ID del tratamiento que se va a marcar como completado
) {
}