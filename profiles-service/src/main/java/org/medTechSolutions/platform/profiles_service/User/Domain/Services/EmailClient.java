package org.medTechSolutions.platform.profiles_service.User.Domain.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailClient {

    private final RestTemplate restTemplate;
    private final String emailServiceUrl;

    public EmailClient(RestTemplate restTemplate, @Value("${email.service.url}") String emailServiceUrl) {
        this.restTemplate = restTemplate;
        this.emailServiceUrl = emailServiceUrl;
    }

    public void sendEmail(String to, String subject, String body) {
        String url = emailServiceUrl + "/api/email/send-email";
        Map<String, String> request = new HashMap<>();
        request.put("to", to);
        request.put("subject", subject);
        request.put("body", body);
        restTemplate.postForEntity(url, request, String.class);
    }
}