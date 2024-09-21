package org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS;

public record CreateLaboratoryDTO(
        String name,
        String address,
        String phone,
        String email
) {
}
