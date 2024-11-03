package org.medTechSolutions.platform.appointments_service.Appointments.Infrastructure.clients;

import org.medTechSolutions.platform.appointments_service.Appointments.Interfaces.REST.clientsDTO.ChatRoomRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "chat-service", url = "localhost:8005")
public interface ChatServiceClient {

    @PostMapping("/chat/rooms")
    void createChatRoom(@RequestBody ChatRoomRequest chatRoomRequest);
}
