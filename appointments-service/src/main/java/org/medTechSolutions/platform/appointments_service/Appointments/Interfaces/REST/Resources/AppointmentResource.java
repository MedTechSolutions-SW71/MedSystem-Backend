package org.medTechSolutions.platform.appointments_service.Appointments.Interfaces.REST.Resources;

public record AppointmentResource(
        Long id,
        Long doctorId,
        Long patientId,
        String date,
        String reason
) { }
