package org.medTechSolutions.platform.profiles_service.User.Domain.Model.Queries;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.ValueObjects.Specialities;

public record GetDoctorBySpecialityQuery(Specialities specialities) {
}
