package org.medTechSolutions.platform.chat_service.ChatRoom.interfaces.rest.resources;

public record ChatRoomResponse(
        String id,
        String appointmentId,
        String doctorId,
        String patientId
) {
}
