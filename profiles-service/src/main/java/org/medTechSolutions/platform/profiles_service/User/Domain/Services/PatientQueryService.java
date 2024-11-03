package org.medTechSolutions.platform.profiles_service.User.Domain.Services;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates.Patient;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Queries.GetAllPatientQuery;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Queries.GetPatientByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PatientQueryService {
    Optional<Patient> handle (GetPatientByIdQuery query);
    List<Patient> handle (GetAllPatientQuery query);
}
