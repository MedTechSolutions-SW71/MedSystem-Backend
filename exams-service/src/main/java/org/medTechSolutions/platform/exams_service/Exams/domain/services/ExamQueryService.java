package org.medTechSolutions.platform.exams_service.Exams.domain.services;

import org.medTechSolutions.platform.exams_service.Exams.domain.model.aggregates.Exam;
import org.medTechSolutions.platform.exams_service.Exams.domain.model.queries.GetAllExams;
import org.medTechSolutions.platform.exams_service.Exams.domain.model.queries.GetExamsByDoctorIdQuery;
import org.medTechSolutions.platform.exams_service.Exams.domain.model.queries.GetExamsByPatientIdQuery;

import java.util.List;

public interface ExamQueryService {
    List<Exam> handle(GetExamsByDoctorIdQuery query);
    List<Exam> handle(GetExamsByPatientIdQuery query);
    List<Exam> handle(GetAllExams query);
}
