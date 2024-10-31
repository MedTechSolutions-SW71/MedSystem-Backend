package org.medTechSolutions.platform.treatments_service.domain.model.queries;

public record GetReportByIdQuery(Long id) {
    public GetReportByIdQuery {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Id cannot be null and must be greater than 0");
        }
    }
}