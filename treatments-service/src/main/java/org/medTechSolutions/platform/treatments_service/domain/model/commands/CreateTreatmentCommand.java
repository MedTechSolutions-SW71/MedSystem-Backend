package org.medTechSolutions.platform.treatments_service.domain.model.commands;

import java.util.List;


public record CreateTreatmentCommand(
        Long patientId,
        Long doctorId,
        String description,
        List<Long> examResultIds
) {
}