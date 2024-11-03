package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources;

public record UpdatePatientResource(
        String firstName,
        String lastName,
        Integer age,
        String address,
        String phone,
        String email
) {
}
