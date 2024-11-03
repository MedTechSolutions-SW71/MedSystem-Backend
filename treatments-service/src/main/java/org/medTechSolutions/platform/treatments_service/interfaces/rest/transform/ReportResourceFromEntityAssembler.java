package org.medTechSolutions.platform.treatments_service.interfaces.rest.transform;

import org.medTechSolutions.platform.treatments_service.domain.model.entities.Report;
import org.medTechSolutions.platform.treatments_service.interfaces.rest.resources.ReportResource;

public class ReportResourceFromEntityAssembler {
    public static ReportResource toResourceFromEntity(Report entity) {
        return new ReportResource(
                entity.getId(),
                entity.getReason(),
                entity.getDate(),
                entity.getPatientId()
        );
    }
}