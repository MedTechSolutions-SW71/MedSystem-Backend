package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources;

public record CreatePatientResource(
        String email,
        String role
) {
}
