package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.ValueObjects.Specialities;

public record UpdateDoctorResource(
        String firstName,
        String lastName,
        Integer licenceNumber,
        Specialities specialities,
        String phone
        //Long idLaboratory
) {
}
