package org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS;

public record patientDTO(
        Long patientId,
        String firstName,
        String lastName,
        Integer age,
        String address,
        String phone
        // String email --> puede usar el email del usuario autenticado
) {
}
