package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.clientsDTO;

public record UserResource(
        Long id,
        String email,
        String role
) {
}
