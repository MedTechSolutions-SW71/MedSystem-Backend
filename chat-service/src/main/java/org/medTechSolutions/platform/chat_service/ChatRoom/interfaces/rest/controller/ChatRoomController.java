package org.medTechSolutions.platform.chat_service.ChatRoom.interfaces.rest.controller;

import org.medTechSolutions.platform.chat_service.ChatRoom.domain.model.ChatRoomModel;
import org.medTechSolutions.platform.chat_service.ChatRoom.domain.service.ChatRoomService;
import org.medTechSolutions.platform.chat_service.ChatRoom.interfaces.rest.resources.ChatRoomRequest;
import org.medTechSolutions.platform.chat_service.ChatRoom.interfaces.rest.resources.ChatRoomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @PostMapping("/chat/rooms")
    public ChatRoomResponse createChatRoom(@RequestBody ChatRoomRequest chatRoomRequest) {
        return chatRoomService.createChatRoom(chatRoomRequest);
    }
}
