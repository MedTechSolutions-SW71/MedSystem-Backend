package org.medTechSolutions.platform.security_service.auth.interfaces.rest.clientDTOS;

public record DoctorDTO(
        Long doctorId,
        String firstName,
        String lastName,
        String specialization,
        Integer licenceNumber,
        String phone
) {
}
