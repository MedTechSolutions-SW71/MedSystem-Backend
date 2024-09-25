package org.medTechSolutions.platform.appointments_service.Appointments.Interfaces.REST.Resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateAppointmentResource(
        @NotNull(message = "Doctor ID is required")
        Long doctorId,

        @NotNull(message = "Patient ID is required")
        Long patientId,

        @NotNull(message = "Date is required")
        LocalDate date,

        @NotBlank(message = "Reason is required")
        String reason
) { }
