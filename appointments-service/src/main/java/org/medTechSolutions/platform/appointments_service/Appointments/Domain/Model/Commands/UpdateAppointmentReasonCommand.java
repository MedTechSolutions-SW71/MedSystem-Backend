package org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Commands;

public record UpdateAppointmentReasonCommand(Long appointmentId, String reason) {
}
