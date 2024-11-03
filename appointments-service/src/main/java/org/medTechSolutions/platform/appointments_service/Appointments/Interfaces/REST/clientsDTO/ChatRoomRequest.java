package org.medTechSolutions.platform.appointments_service.Appointments.Interfaces.REST.clientsDTO;

public record ChatRoomRequest(
        String appointmentId,
        String doctorId,
        String patientId
) {
}
