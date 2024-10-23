package org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.ValueObjects.Specialities;

public record CreateDoctorCommand(
        Long id,
        String email,
        String role
) {
}
