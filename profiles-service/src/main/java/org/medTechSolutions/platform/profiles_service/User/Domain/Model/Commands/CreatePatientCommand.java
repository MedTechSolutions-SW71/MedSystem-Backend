package org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands;

public record CreatePatientCommand(
        Long id,
        String email,
        String role
) {
}
