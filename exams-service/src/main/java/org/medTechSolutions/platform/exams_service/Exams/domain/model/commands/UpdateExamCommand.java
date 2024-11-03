package org.medTechSolutions.platform.exams_service.Exams.domain.model.commands;

import java.time.LocalDate;

public record UpdateExamCommand(Long examId, Boolean examResultsReady, String examResultsUrl) {
}
