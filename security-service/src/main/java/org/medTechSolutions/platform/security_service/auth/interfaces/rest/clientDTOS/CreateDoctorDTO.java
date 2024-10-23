package org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS;

public record CreateDoctorDTO(
        String firstName,
        String lastName,
        Integer licenceNumber,
        String speciality,
        String phone,
        String email
) {
}
