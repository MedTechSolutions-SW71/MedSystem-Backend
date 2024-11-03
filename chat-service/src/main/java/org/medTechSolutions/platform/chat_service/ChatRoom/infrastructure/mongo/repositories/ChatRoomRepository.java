package org.medTechSolutions.platform.chat_service.ChatRoom.infrastructure.mongo.repositories;

import org.medTechSolutions.platform.chat_service.ChatRoom.domain.model.ChatRoomModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoomModel, String> {
    Optional<ChatRoomModel> findByAppointmentId(String appointmentId);

}
