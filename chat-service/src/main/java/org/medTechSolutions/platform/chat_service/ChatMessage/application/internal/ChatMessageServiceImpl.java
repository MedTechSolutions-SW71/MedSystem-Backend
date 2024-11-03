package org.medTechSolutions.platform.chat_service.ChatMessage.application.internal;

import org.medTechSolutions.platform.chat_service.ChatMessage.domain.model.ChatMessageModel;
import org.medTechSolutions.platform.chat_service.ChatMessage.domain.service.ChatMessageService;
import org.medTechSolutions.platform.chat_service.ChatMessage.infrastructure.mongo.repositories.ChatMessageRepository;
import org.medTechSolutions.platform.chat_service.ChatMessage.interfaces.resources.dto.ChatMessageDTO;
import org.medTechSolutions.platform.chat_service.ChatRoom.domain.model.ChatRoomModel;
import org.medTechSolutions.platform.chat_service.ChatRoom.infrastructure.mongo.repositories.ChatRoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;


    public ChatMessageServiceImpl(ChatMessageRepository chatMessageRepository, ChatRoomRepository chatRoomRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    @Override
    public ChatMessageDTO saveMessage(String roomId, String senderId, String content) {
        ChatMessageModel message = new ChatMessageModel();
        message.setRoomId(roomId);
        message.setSenderId(senderId);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());

        ChatMessageModel savedMessage = chatMessageRepository.save(message);

        // Convertir a DTO para devolver
        return new ChatMessageDTO(
                savedMessage.getContent(),
                savedMessage.getSenderId(),
                savedMessage.getTimestamp()
        );
    }

    @Override
    public List<ChatMessageDTO> getMessagesByRoomId(String roomId) {
        return chatMessageRepository.findByRoomId(roomId).stream()
                .map(model -> new ChatMessageDTO(model.getContent(), model.getSenderId(), model.getTimestamp()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAuthorized(String roomId, String doctorId, String patientId) {
        Optional<ChatRoomModel> chatRoom = chatRoomRepository.findByAppointmentId(roomId);
        return chatRoom.isPresent() &&
                chatRoom.get().getDoctorId().equals(doctorId) &&
                chatRoom.get().getPatientId().equals(patientId);
    }
}
