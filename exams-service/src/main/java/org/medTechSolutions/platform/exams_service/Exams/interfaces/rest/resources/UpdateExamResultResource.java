package org.medTechSolutions.platform.exams_service.Exams.interfaces.rest.resources;

import java.time.LocalDate;

public record UpdateExamResultResource(Boolean examResultsReady, String examResultsUrl) {
}
