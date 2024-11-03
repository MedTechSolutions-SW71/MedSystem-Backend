package org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands;

public record UpdatePatientCommand(Long id, String firstName, String lastName, Integer age, String address, String phone, String email) {
}
