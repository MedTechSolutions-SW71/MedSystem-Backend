package org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands;

public record CreateLaboratoryCommand(String name, String address, String phone, String email) {
}