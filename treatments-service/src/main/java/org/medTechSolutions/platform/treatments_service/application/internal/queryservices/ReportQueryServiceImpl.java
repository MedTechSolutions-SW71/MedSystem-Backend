package org.medTechSolutions.platform.treatments_service.application.internal.queryservices;


import org.medTechSolutions.platform.treatments_service.domain.model.entities.Report;
import org.medTechSolutions.platform.treatments_service.domain.model.queries.GetAllReportsQuery;
import org.medTechSolutions.platform.treatments_service.domain.model.queries.GetReportByIdQuery;
import org.medTechSolutions.platform.treatments_service.domain.model.queries.GetReportsByPatientIdQuery;
import org.medTechSolutions.platform.treatments_service.domain.services.ReportQueryService;
import org.medTechSolutions.platform.treatments_service.infrastucture.persistence.jpa.repositories.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportQueryServiceImpl implements ReportQueryService {
    private final ReportRepository reportRepository;

    public ReportQueryServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Optional<Report> handle(GetReportByIdQuery query) {
        return reportRepository.findById(query.id());
    }

    @Override
    public List<Report> handle(GetReportsByPatientIdQuery query) {
        return reportRepository.findAllReportsByPatientId(query.patientId());
    }

    @Override
    public List<Report> handle(GetAllReportsQuery query) {
        return reportRepository.findAll();
    }
}