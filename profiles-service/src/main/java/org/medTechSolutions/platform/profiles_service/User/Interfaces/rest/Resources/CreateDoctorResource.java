package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.ValueObjects.Specialities;

public record CreateDoctorResource(
        String email,
        String role
        //Long idLaboratory
) {
}
