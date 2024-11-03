package org.medTechSolutions.platform.chat_service.ChatMessage.domain.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "chat_messages")
public class ChatMessageModel {
    @Id
    private String id;
    private String roomId;          // Relación con ChatRoom
    private String senderId;        // ID del remitente (puede ser doctorId o patientId)
    private String content;         // Contenido del mensaje
    private LocalDateTime timestamp; // Hora en que se envió el mensaje

}
