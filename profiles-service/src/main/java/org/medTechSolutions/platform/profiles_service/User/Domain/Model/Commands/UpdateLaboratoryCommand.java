package org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands;

public record UpdateLaboratoryCommand(Long id, String name, String address, String phone, String email) {
}