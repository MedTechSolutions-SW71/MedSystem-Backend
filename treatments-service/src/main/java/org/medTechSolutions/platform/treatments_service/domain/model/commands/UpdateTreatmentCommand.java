package org.medTechSolutions.platform.treatments_service.domain.model.commands;

import java.util.List;

public record UpdateTreatmentCommand(Long treatmentId, Long patientId, Long doctorId, String description, List<Long> examResultIds, boolean isCompleted) {
    public UpdateTreatmentCommand {
        if (treatmentId == null || treatmentId < 0) {
            throw new IllegalArgumentException("TreatmentId cannot be null or less than 0");
        }
        if (patientId == null || patientId < 0) {
            throw new IllegalArgumentException("PatientId cannot be null or less than 0");
        }
        if (doctorId == null || doctorId < 0) {
            throw new IllegalArgumentException("DoctorId cannot be null or less than 0");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
    }
}