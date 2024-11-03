package org.medTechSolutions.platform.treatments_service.application.internal.commandservices;

import org.medTechSolutions.platform.treatments_service.domain.model.commands.CreateReportCommand;
import org.medTechSolutions.platform.treatments_service.domain.model.entities.Report;
import org.medTechSolutions.platform.treatments_service.domain.services.ReportCommandService;
import org.medTechSolutions.platform.treatments_service.infrastucture.persistence.jpa.repositories.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportCommandServiceImpl implements ReportCommandService {
    private final ReportRepository reportRepository;

    public ReportCommandServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Optional<Report> handle(CreateReportCommand command) {
        if (reportRepository.existsByDateAndReasonAndPatientId(command.date(), command.reason(), command.patientId())) {
            throw new IllegalArgumentException("Report already exists");
        }
        var report = new Report(command);
        var createdReport = reportRepository.save(report);
        return Optional.of(createdReport);
    }
}