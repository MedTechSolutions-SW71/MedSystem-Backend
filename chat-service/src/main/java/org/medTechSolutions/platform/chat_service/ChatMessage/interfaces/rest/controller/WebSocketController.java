package org.medTechSolutions.platform.chat_service.ChatMessage.interfaces.rest.controller;

import org.medTechSolutions.platform.chat_service.ChatMessage.domain.model.ChatMessageModel;
import org.medTechSolutions.platform.chat_service.ChatMessage.domain.service.ChatMessageService;
import org.medTechSolutions.platform.chat_service.ChatMessage.interfaces.resources.dto.ChatMessageDTO;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class WebSocketController {

    private final ChatMessageService chatMessageService;

    public WebSocketController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessageDTO chat(@DestinationVariable String roomId, @Payload ChatMessageDTO message) {
        System.out.println("Message received: " + message.getContent());

        // Guardar el mensaje en MongoDB y devolverlo como DTO
        return chatMessageService.saveMessage(roomId, message.getUser(), message.getContent());
    }
}
