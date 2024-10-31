package org.medTechSolutions.platform.treatments_service.domain.model.queries;

public record GetReportsByPatientIdQuery(Long patientId) {
    public GetReportsByPatientIdQuery {
        if (patientId == null || patientId < 0) {
            throw new IllegalArgumentException("Patient ID cannot be null or less than 0");
        }
    }
}