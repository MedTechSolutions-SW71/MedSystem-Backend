package org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS;

public record createDoctorDTO(
        String firstName,
        String lastName,
        String specialization,
        Integer licenceNumber,
        String phone
        // String email --> puede usar el email del usuario autenticado
) {
}
