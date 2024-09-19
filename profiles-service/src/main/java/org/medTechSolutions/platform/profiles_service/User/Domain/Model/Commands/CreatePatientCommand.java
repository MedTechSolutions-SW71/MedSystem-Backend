package org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands;

public record CreatePatientCommand(String firstName, String lastName, Integer age, String address, String phone, String email) {
}
