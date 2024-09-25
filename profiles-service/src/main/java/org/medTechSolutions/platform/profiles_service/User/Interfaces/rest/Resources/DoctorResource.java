package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources;

public record DoctorResource(
        Long id,
        String firstName,
        String lastName,
        String specialization,
        Integer licenceNumber,
        String phone,
        String email
        //Long idLaboratory
        ) {
}
