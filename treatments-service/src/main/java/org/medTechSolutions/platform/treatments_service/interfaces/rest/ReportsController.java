package org.medTechSolutions.platform.treatments_service.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.medTechSolutions.platform.treatments_service.domain.model.entities.Report;
import org.medTechSolutions.platform.treatments_service.domain.model.queries.GetAllReportsQuery;
import org.medTechSolutions.platform.treatments_service.domain.model.queries.GetReportByIdQuery;
import org.medTechSolutions.platform.treatments_service.domain.model.queries.GetReportsByPatientIdQuery;
import org.medTechSolutions.platform.treatments_service.domain.services.ReportCommandService;
import org.medTechSolutions.platform.treatments_service.domain.services.ReportQueryService;
import org.medTechSolutions.platform.treatments_service.interfaces.rest.resources.CreateReportResource;
import org.medTechSolutions.platform.treatments_service.interfaces.rest.resources.ReportResource;
import org.medTechSolutions.platform.treatments_service.interfaces.rest.transform.CreateReportCommandFromResourceAssembler;
import org.medTechSolutions.platform.treatments_service.interfaces.rest.transform.ReportResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/reports")
@Tag(name = "Reports", description = "Report Management Endpoints")
public class ReportsController {
    private final ReportCommandService reportCommandService;
    private final ReportQueryService reportQueryService;

    public ReportsController(ReportCommandService reportCommandService, ReportQueryService reportQueryService) {
        this.reportCommandService = reportCommandService;
        this.reportQueryService = reportQueryService;
    }

    @PostMapping
    public ResponseEntity<ReportResource> createReport(@RequestBody CreateReportResource resource) {
        Optional<Report> report = reportCommandService.handle(
                CreateReportCommandFromResourceAssembler.toCommandFromResource(resource));
        return report.map(source -> new ResponseEntity<>(
                        ReportResourceFromEntityAssembler.toResourceFromEntity(source),CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportResource> getReportById(@PathVariable Long id) {
        var getReportByIdQuery = new GetReportByIdQuery(id);
        Optional<Report> report = reportQueryService.handle(getReportByIdQuery);
        if (report.isEmpty()) return ResponseEntity.notFound().build();
        return report.map(source -> ResponseEntity.ok(ReportResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("patientId/{patientId}")
    public ResponseEntity<List<ReportResource>> getAllReportsByPatientId(@PathVariable Long patientId) {
        var getReportsByPatientIdQuery = new GetReportsByPatientIdQuery(patientId);
        List<Report> reports = reportQueryService.handle(getReportsByPatientIdQuery);
        if (reports.isEmpty()) return ResponseEntity.notFound().build();
        var reportsResources =reports.stream().map(ReportResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(reportsResources);
    }

    @GetMapping
    public ResponseEntity<List<ReportResource>> getAllReports() {
        var getAllReportsQuery = new GetAllReportsQuery();
        List<Report> reports = reportQueryService.handle(getAllReportsQuery);
        if (reports.isEmpty()) return ResponseEntity.notFound().build();
        var reportsResources =reports.stream().map(ReportResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(reportsResources);
    }
}