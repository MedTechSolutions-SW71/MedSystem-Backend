package org.medTechSolutions.platform.treatments_service.domain.model.queries;

public record GetTreatmentByPatientIdQuery(Long patientId) {
    public GetTreatmentByPatientIdQuery {
        if (patientId == null || patientId < 0) {
            throw new IllegalArgumentException("Patient ID cannot be null or less than 0");
        }
    }
}