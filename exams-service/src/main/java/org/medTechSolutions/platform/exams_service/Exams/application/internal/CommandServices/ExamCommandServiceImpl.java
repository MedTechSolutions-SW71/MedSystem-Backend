package org.medTechSolutions.platform.exams_service.Exams.application.internal.CommandServices;

import org.medTechSolutions.platform.exams_service.Exams.domain.model.aggregates.Exam;
import org.medTechSolutions.platform.exams_service.Exams.domain.model.commands.CreateExamCommand;
import org.medTechSolutions.platform.exams_service.Exams.domain.model.commands.UpdateExamCommand;
import org.medTechSolutions.platform.exams_service.Exams.domain.services.ExamCommandService;
import org.medTechSolutions.platform.exams_service.Exams.infrastructure.persistence.jpa.repositories.ExamRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExamCommandServiceImpl implements ExamCommandService {
    private final ExamRepository examRepository;

    public ExamCommandServiceImpl(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public Optional<Exam> handle(CreateExamCommand command) {
        var exam = new Exam(command);
        examRepository.save(exam);
        return Optional.of(exam);
    }

    public Optional<Exam> handle(UpdateExamCommand command) {
        var result = examRepository.findById(command.examId());
        if(result.isEmpty()) throw new IllegalArgumentException("Exam not found");
        var examToUpdate = result.get();
        try {
            var updatedExam = examRepository.save(examToUpdate.updateExamResult(command.examResultsReady(),command.examResultsUrl()));
            return Optional.of(updatedExam);
        }catch (Exception e) {
            throw new IllegalArgumentException("Failed to update exam result" + e.getMessage());
        }
    }


}
