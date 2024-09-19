package org.medTechSolutions.platform.profiles_service.User.Domain.Services;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Aggregates.Doctor;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Queries.GetAllDoctorQuery;
import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Queries.GetDoctorByIdQuery;

import java.util.List;
import java.util.Optional;

public interface DoctorQueryService {
    Optional<Doctor> handle (GetDoctorByIdQuery query);
    List<Doctor> handle (GetAllDoctorQuery query);
}
