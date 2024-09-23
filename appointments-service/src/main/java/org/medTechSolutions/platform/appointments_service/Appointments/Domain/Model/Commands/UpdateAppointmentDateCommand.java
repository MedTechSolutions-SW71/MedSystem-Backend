package org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Commands;

public record UpdateAppointmentDateCommand(Long appointmentId, String date) {
}
