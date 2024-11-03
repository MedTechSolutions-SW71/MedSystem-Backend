package org.medTechSolutions.platform.email_service.Email.Interfaces.Rest;

import jakarta.mail.MessagingException;
import org.medTechSolutions.platform.email_service.Email.Application.Internal.Commands.CommandsServices.EmailCommandServiceImpl;
import org.medTechSolutions.platform.email_service.Email.Domain.Model.Commands.SendEmailCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    private final EmailCommandServiceImpl emailService;

    public EmailController(EmailCommandServiceImpl emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody SendEmailCommand command){
        try {
            emailService.handle(command);
            return ResponseEntity.ok("Email sent");
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("Failed to send email" + e.getMessage());
        }

    }
}
