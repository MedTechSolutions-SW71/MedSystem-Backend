package org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Commands;

import java.time.LocalDate;

public record UpdateAppointmentDateCommand(Long appointmentId, LocalDate date) {
}
