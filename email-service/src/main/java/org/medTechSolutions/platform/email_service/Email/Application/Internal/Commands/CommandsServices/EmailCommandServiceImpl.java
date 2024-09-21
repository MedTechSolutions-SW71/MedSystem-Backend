package org.medTechSolutions.platform.email_service.Email.Application.Internal.Commands.CommandsServices;

import org.medTechSolutions.platform.email_service.Email.Domain.Model.Aggregates.Email;
import org.medTechSolutions.platform.email_service.Email.Domain.Model.Commands.SendEmailCommand;
import org.medTechSolutions.platform.email_service.Email.Infrastructure.EmailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailCommandServiceImpl {
    private final EmailSender emailSender;

    public EmailCommandServiceImpl(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void handle(SendEmailCommand command){
        Email email = new Email(command);
        emailSender.send(email);
    }
}
