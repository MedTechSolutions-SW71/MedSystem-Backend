package org.medTechSolutions.platform.appointments_service.Appointments.Interfaces.REST.Transform;

import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Commands.UpdateAppointmentReasonCommand;
import org.medTechSolutions.platform.appointments_service.Appointments.Interfaces.REST.Resources.UpdateAppointmentReasonResource;

public class UpdateAppointmentReasonCommandFromResourceAssembler {
    public static UpdateAppointmentReasonCommand toCommandFromResource(Long appointmentId, UpdateAppointmentReasonResource resource) {
        return new UpdateAppointmentReasonCommand(appointmentId, resource.reason());
    }
}
