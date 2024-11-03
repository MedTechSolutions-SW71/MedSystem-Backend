package org.medTechSolutions.platform.email_service.Email.Application.Internal.Commands.CommandsServices;

import jakarta.mail.MessagingException;
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

    // cuando el usaurio se registra se envia un mensaje de bienvenida a su correo (estatico)
    public void handle(SendEmailCommand command) throws MessagingException {
        Email email = new Email(command);
        emailSender.send(email);
    }

    // cuando el usuario reserva una cita se envia a su correo el recordatorio con el detalle de la cita (dinamico)


    // Cuando el usuario cancela su cita, se envia un correo de cancelacion (estatico)
}
