package org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Commands;

public record CreateAppointmentCommand(Long doctorId, Long patientId, String date, String reason) {
}
