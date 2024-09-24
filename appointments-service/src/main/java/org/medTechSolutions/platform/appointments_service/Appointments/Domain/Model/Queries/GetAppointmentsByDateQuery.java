package org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Queries;

import java.time.LocalDate;


public record GetAppointmentsByDateQuery(LocalDate date) {
}
