package org.medTechSolutions.platform.chat_service.ChatRoom.domain.service;

import org.medTechSolutions.platform.chat_service.ChatRoom.domain.model.ChatRoomModel;
import org.medTechSolutions.platform.chat_service.ChatRoom.interfaces.rest.resources.ChatRoomRequest;
import org.medTechSolutions.platform.chat_service.ChatRoom.interfaces.rest.resources.ChatRoomResponse;

import java.util.Optional;

public interface ChatRoomService {
    ChatRoomResponse createChatRoom(ChatRoomRequest chatRoomRequest);
    Optional<ChatRoomModel> findChatRoomByAppointmentId(String appointmentId);
}
