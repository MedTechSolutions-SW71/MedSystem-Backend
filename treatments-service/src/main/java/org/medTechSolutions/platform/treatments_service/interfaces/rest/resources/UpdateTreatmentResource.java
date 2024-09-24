package org.medTechSolutions.platform.treatments_service.interfaces.rest.resources;

import java.util.List;

public record UpdateTreatmentResource(
        Long treatmentId,
        Long patientId,
        Long doctorId,
        String description,
        List<Long> examResultIds,
        boolean isCompleted
) {
}