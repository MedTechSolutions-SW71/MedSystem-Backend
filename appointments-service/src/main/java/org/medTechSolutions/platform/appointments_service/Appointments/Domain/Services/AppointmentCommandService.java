package org.medTechSolutions.platform.appointments_service.Appointments.Domain.Services;

import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Aggregates.Appointment;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Commands.CreateAppointmentCommand;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Commands.DeleteAppointmentCommand;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Commands.UpdateAppointmentDateCommand;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Commands.UpdateAppointmentReasonCommand;

import java.util.Optional;

public interface AppointmentCommandService {
    Optional<Appointment> handle(CreateAppointmentCommand command);
    Optional<Appointment> handle(UpdateAppointmentReasonCommand command);
    Optional<Appointment> handle(UpdateAppointmentDateCommand command);

    void handle(DeleteAppointmentCommand command);
}
