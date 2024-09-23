package org.medTechSolutions.platform.appointments_service.Appointments.Domain.Services;

import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Aggregates.Appointment;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Queries.*;

import java.util.List;
import java.util.Optional;

public interface AppointmentQueryService {
    Optional<Appointment> handle(GetAppointmentsByIdQuery query);
    List<Appointment> handle(GetAllAppointmentsByDoctorIdQuery query);
    List<Appointment> handle(GetAllAppointmentsByPatientIdQuery query);
    List<Appointment> handle(GetAppointmentsByDateQuery query);
    List<Appointment> handle(GetAllAppointmentsQuery query);
}
