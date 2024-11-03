package org.medTechSolutions.platform.treatments_service.domain.services;

import org.medTechSolutions.platform.treatments_service.domain.model.entities.Report;
import org.medTechSolutions.platform.treatments_service.domain.model.queries.GetAllReportsQuery;
import org.medTechSolutions.platform.treatments_service.domain.model.queries.GetReportByIdQuery;
import org.medTechSolutions.platform.treatments_service.domain.model.queries.GetReportsByPatientIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReportQueryService {
    Optional<Report> handle(GetReportByIdQuery query);
    List<Report> handle(GetReportsByPatientIdQuery query);
    List<Report> handle(GetAllReportsQuery query);
}