package org.medTechSolutions.platform.exams_service.Exams.domain.services;

import org.medTechSolutions.platform.exams_service.Exams.domain.model.aggregates.Exam;
import org.medTechSolutions.platform.exams_service.Exams.domain.model.commands.CreateExamCommand;
import org.medTechSolutions.platform.exams_service.Exams.domain.model.commands.UpdateExamCommand;

import java.util.Optional;

public interface ExamCommandService {
    Optional<Exam> handle(CreateExamCommand command);
    Optional<Exam> handle(UpdateExamCommand command);
}
