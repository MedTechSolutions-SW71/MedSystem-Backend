package org.medTechSolutions.platform.appointments_service.Appointments.Interfaces.REST.Transform;

import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Commands.UpdateAppointmentDateCommand;
import org.medTechSolutions.platform.appointments_service.Appointments.Interfaces.REST.Resources.UpdateAppointmentDateResource;

public class UpdateAppointmentDateCommandFromResourceAssembler {
    public static UpdateAppointmentDateCommand toCommandFromResource(Long appointmentId, UpdateAppointmentDateResource resource) {
        return new UpdateAppointmentDateCommand(appointmentId, resource.date());
    }
}
