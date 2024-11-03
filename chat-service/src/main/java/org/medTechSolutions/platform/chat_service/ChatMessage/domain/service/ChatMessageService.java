package org.medTechSolutions.platform.chat_service.ChatMessage.domain.service;

import org.medTechSolutions.platform.chat_service.ChatMessage.domain.model.ChatMessageModel;
import org.medTechSolutions.platform.chat_service.ChatMessage.interfaces.resources.dto.ChatMessageDTO;

import java.util.List;

public interface ChatMessageService {
    ChatMessageDTO saveMessage(String roomId, String senderId, String content);
    List<ChatMessageDTO> getMessagesByRoomId(String roomId);
    boolean isAuthorized(String roomId, String doctorId, String patientId); // Nuevo método de autorización
}

