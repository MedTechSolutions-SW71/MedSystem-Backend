package org.medTechSolutions.platform.chat_service.ChatMessage.interfaces.rest.controller;

import org.medTechSolutions.platform.chat_service.ChatMessage.domain.service.ChatMessageService;
import org.medTechSolutions.platform.chat_service.ChatMessage.interfaces.resources.dto.ChatMessageDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ChatHistoryController {

    private final ChatMessageService chatMessageService;

    public ChatHistoryController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @GetMapping("/chat/history/{roomId}")
    public List<ChatMessageDTO> getChatHistory(@PathVariable String roomId) {
        return chatMessageService.getMessagesByRoomId(roomId);
    }
}
