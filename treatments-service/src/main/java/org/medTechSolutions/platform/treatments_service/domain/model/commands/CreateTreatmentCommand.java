package org.medTechSolutions.platform.treatments_service.domain.model.commands;

import java.util.List;


public record CreateTreatmentCommand(String treatmentName, String description, String startDate, String endDate, Long patientId) {
    public CreateTreatmentCommand {
        if (treatmentName == null || treatmentName.isEmpty()) {
            throw new IllegalArgumentException("TreatmentName cannot be null or empty");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (startDate == null || startDate.isEmpty()) {
            throw new IllegalArgumentException("StartDate cannot be null or empty");
        }
        if (endDate == null || endDate.isEmpty()) {
            throw new IllegalArgumentException("EndDate cannot be null or empty");
        }
        if (patientId == null || patientId < 0) {
            throw new IllegalArgumentException("PatientId cannot be null or less than 0");
        }
    }
}