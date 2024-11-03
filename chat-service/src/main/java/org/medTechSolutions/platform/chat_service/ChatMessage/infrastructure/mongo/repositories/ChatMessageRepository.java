package org.medTechSolutions.platform.chat_service.ChatMessage.infrastructure.mongo.repositories;

import org.medTechSolutions.platform.chat_service.ChatMessage.domain.model.ChatMessageModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessageModel, String> {
    List<ChatMessageModel> findByRoomId(String roomId);

}
