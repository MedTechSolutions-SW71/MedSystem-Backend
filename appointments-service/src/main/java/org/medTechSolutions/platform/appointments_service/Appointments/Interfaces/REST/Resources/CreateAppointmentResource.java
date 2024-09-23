package org.medTechSolutions.platform.appointments_service.Appointments.Interfaces.REST.Resources;

public record CreateAppointmentResource(
        Long doctorId,
        Long patientId,
        String date,
        String reason
) { }
