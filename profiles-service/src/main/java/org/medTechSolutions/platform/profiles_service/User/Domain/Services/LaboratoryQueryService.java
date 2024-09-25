package org.medTechSolutions.platform.profiles_service.User.Domain.Services;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates.Laboratory;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Queries.GetAllLaboratoriesQuery;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Queries.GetLaboratoryByIdQuery;

import java.util.List;
import java.util.Optional;

public interface LaboratoryQueryService {
    Optional<Laboratory> handle (GetLaboratoryByIdQuery query);
    List<Laboratory> handle (GetAllLaboratoriesQuery query);
}