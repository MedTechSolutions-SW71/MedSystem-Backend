package org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands;

public record CreateDoctorCommand(
        String firstName,
        String lastName,
        String specialization,
        Integer licenceNumber ,
        String phone,
        String email
        //Long idLaboratory
) {
}
