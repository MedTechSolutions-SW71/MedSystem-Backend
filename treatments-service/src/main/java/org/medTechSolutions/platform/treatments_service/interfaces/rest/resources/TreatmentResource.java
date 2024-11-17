package org.medTechSolutions.platform.treatments_service.interfaces.rest.resources;

public record TreatmentResource(Long id, String treatmentName, String description, String period, Long patientId, Long doctorId) {
}