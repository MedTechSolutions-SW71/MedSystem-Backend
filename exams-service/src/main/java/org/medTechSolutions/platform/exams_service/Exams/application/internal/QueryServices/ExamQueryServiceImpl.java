package org.medTechSolutions.platform.exams_service.Exams.application.internal.QueryServices;

import org.medTechSolutions.platform.exams_service.Exams.domain.model.aggregates.Exam;
import org.medTechSolutions.platform.exams_service.Exams.domain.model.queries.GetAllExams;
import org.medTechSolutions.platform.exams_service.Exams.domain.model.queries.GetExamsByDoctorIdQuery;
import org.medTechSolutions.platform.exams_service.Exams.domain.model.queries.GetExamsByPatientIdQuery;
import org.medTechSolutions.platform.exams_service.Exams.domain.services.ExamQueryService;
import org.medTechSolutions.platform.exams_service.Exams.infrastructure.persistence.jpa.repositories.ExamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamQueryServiceImpl implements ExamQueryService {
    private final ExamRepository examRepository;

    public ExamQueryServiceImpl(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }


    @Override
    public List<Exam> handle(GetExamsByDoctorIdQuery query) {
        return examRepository.findByDoctorId(query.doctorId());
    }

    @Override
    public List<Exam> handle(GetExamsByPatientIdQuery query) {
        return examRepository.findByPatientId(query.patientId());
    }

    @Override
    public List<Exam> handle(GetAllExams query) {
        return examRepository.findAll();
    }
}
