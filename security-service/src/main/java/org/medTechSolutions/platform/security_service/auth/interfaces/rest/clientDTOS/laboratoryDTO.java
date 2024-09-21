package org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS;

public record laboratoryDTO(
        Long laboratoryId,
        String name,
        String address,
        String phone
        // String email --> puede usar el email del usuario autenticado
) {
}
