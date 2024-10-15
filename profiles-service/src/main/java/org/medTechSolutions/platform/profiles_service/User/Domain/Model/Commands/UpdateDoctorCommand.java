package org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.ValueObjects.Specialities;

public record UpdateDoctorCommand(
        Long id,
        String firstName,
        String lastName,
        Integer licenceNumber,
        Specialities specialities,
        String phone,
        String email
        //Long idLaboratory
) {
}
