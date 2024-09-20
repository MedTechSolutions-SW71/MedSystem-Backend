package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources;

public record DoctorResource(Long id, String firstName, String lastname, String specialization, Integer licenceNumber, String phone, String email, Long idLaboratory) {
}
