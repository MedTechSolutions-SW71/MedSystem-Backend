package org.medTechSolutions.platform.email_service.Email.Domain.Model.Commands;

public record SendEmailCommand(String to, String subject, String body) {
}
