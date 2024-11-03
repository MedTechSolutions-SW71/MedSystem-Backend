package org.medTechSolutions.platform.chat_service.ChatMessage.interfaces.resources.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ChatMessageDTO {
    private String content;
    private String user;
    private LocalDateTime timestamp;
}
