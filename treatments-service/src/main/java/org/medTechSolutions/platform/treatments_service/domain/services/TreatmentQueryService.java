package org.medTechSolutions.platform.treatments_service.domain.services;


import org.medTechSolutions.platform.treatments_service.domain.model.aggregates.Treatment;
import org.medTechSolutions.platform.treatments_service.domain.model.queries.GetAllTreatmentsQuery;
import org.medTechSolutions.platform.treatments_service.domain.model.queries.GetTreatmentByPatientIdQuery;

import java.util.List;
import java.util.Optional;

public interface TreatmentQueryService {
    List<Treatment> handle(GetAllTreatmentsQuery query);
    Optional<Treatment> handle(GetTreatmentByPatientIdQuery query);
}