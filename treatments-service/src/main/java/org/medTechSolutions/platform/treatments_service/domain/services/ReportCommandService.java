package org.medTechSolutions.platform.treatments_service.domain.services;


import org.medTechSolutions.platform.treatments_service.domain.model.commands.CreateReportCommand;
import org.medTechSolutions.platform.treatments_service.domain.model.entities.Report;

import java.util.Optional;

public interface ReportCommandService {
    Optional<Report> handle(CreateReportCommand command);
}