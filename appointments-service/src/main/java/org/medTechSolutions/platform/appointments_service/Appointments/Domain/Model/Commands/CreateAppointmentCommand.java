package org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Commands;

import java.time.LocalDate;

public record CreateAppointmentCommand(Long doctorId, Long patientId, LocalDate date, String reason) {
}
