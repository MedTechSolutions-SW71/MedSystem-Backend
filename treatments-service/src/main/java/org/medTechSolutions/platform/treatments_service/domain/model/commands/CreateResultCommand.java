package org.medTechSolutions.platform.treatments_service.domain.model.commands;

public record CreateResultCommand(Long doctorId, Long patientId, String typeOfExam, String resultDateTime, boolean result) {
    public CreateResultCommand {
        if (doctorId == null || doctorId < 0) {
            throw new IllegalArgumentException("DoctorId cannot be null or less than 0");
        }
        if (patientId == null || patientId < 0) {
            throw new IllegalArgumentException("PatientId cannot be null or less than 0");
        }
        if (typeOfExam == null || typeOfExam.isEmpty()) {
            throw new IllegalArgumentException("TypeOfExam cannot be null or empty");
        }
        if (resultDateTime == null || resultDateTime.isEmpty()) {
            throw new IllegalArgumentException("ResultDateTime cannot be null or empty");
        }
    }
}