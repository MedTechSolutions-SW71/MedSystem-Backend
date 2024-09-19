package org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands;

public record UpdateDoctorCommand(Long id, String firstName, String lastName, String specialization, Integer licenceNumber, String phone, String email, Long idLaboratory) {
}
