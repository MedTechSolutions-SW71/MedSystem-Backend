package org.medTechSolutions.platform.exams_service.Exams.domain.model.commands;

import java.util.Date;

public record CreateExamCommand(Long doctorId, Long patientId, String examType, Date examDate, Date examResultDate) {
}