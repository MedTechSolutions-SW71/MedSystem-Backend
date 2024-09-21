package org.medTechSolutions.platform.email_service.Email.Infrastructure;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.medTechSolutions.platform.email_service.Email.Domain.Model.Aggregates.Email;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
    private final JavaMailSender javaMailSender;

    public EmailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send(Email email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setText(email.getBody(), true);
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setFrom("galonso097@gmail.com");
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
