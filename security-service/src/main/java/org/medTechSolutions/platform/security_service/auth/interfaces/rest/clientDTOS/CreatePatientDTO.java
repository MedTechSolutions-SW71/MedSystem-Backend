package org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS;

public record CreatePatientDTO(
        String firstName,
        String lastName,
        Integer age,
        String address,
        String phone,
        String email
) {
}
