package org.medTechSolutions.platform.chat_service.ChatRoom.interfaces.rest.resources;

public record ChatRoomRequest(
        String appointmentId,
        String doctorId,
        String patientId
) {
}