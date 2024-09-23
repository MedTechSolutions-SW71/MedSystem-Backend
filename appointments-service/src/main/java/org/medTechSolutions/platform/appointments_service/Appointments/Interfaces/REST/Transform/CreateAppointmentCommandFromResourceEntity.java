package org.medTechSolutions.platform.appointments_service.Appointments.Interfaces.REST.Transform;

import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Commands.CreateAppointmentCommand;
import org.medTechSolutions.platform.appointments_service.Appointments.Interfaces.REST.Resources.CreateAppointmentResource;

public class CreateAppointmentCommandFromResourceEntity {
    public static CreateAppointmentCommand toCommandFromResource(CreateAppointmentResource resource) {
        return new CreateAppointmentCommand(
                resource.doctorId(),
                resource.patientId(),
                resource.date(),
                resource.reason()
        );
    }
}
