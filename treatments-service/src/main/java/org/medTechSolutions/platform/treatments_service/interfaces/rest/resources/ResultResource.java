package org.medTechSolutions.platform.treatments_service.interfaces.rest.resources;

public record ResultResource(
        Long id,
        Long doctorId,
        Long patientId,
        String typeOfExam,
        String resultDateTime,
        boolean result
) {
}