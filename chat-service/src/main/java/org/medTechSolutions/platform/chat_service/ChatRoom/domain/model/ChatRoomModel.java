package org.medTechSolutions.platform.chat_service.ChatRoom.domain.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "chatRoom")
public class ChatRoomModel {
    @Id
    private String id;
    private String appointmentId;
    private String doctorId;
    private String patientId;
    // private boolean active; //  campo para activar/desactivar la sala

}
