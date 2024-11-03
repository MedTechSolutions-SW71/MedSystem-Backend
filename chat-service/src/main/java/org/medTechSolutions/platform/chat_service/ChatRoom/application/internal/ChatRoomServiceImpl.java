package org.medTechSolutions.platform.chat_service.ChatRoom.application.internal;

import org.medTechSolutions.platform.chat_service.ChatRoom.domain.model.ChatRoomModel;
import org.medTechSolutions.platform.chat_service.ChatRoom.domain.service.ChatRoomService;
import org.medTechSolutions.platform.chat_service.ChatRoom.infrastructure.mongo.repositories.ChatRoomRepository;
import org.medTechSolutions.platform.chat_service.ChatRoom.interfaces.rest.resources.ChatRoomRequest;
import org.medTechSolutions.platform.chat_service.ChatRoom.interfaces.rest.resources.ChatRoomResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomServiceImpl(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    @Override
    public ChatRoomResponse createChatRoom(ChatRoomRequest chatRoomRequest) {
        chatRoomRepository.findByAppointmentId(chatRoomRequest.appointmentId())
                .ifPresent(existingRoom -> {
                    throw new IllegalArgumentException("Room already exists for appointment ID: " + chatRoomRequest.appointmentId());
                });

        ChatRoomModel chatRoom = new ChatRoomModel();
        chatRoom.setAppointmentId(chatRoomRequest.appointmentId());
        chatRoom.setDoctorId(chatRoomRequest.doctorId());
        chatRoom.setPatientId(chatRoomRequest.patientId());

        ChatRoomModel savedChatRoom = chatRoomRepository.save(chatRoom);

        return new ChatRoomResponse(
                savedChatRoom.getId(),
                savedChatRoom.getAppointmentId(),
                savedChatRoom.getDoctorId(),
                savedChatRoom.getPatientId()
        );
    }

    @Override
    public Optional<ChatRoomModel> findChatRoomByAppointmentId(String appointmentId) {
        return chatRoomRepository.findByAppointmentId(appointmentId);
    }
}
