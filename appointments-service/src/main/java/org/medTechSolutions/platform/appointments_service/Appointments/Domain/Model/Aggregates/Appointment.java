package org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Aggregates;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Commands.CreateAppointmentCommand;
import org.medTechSolutions.platform.appointments_service.Shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Appointment extends AuditableAbstractAggregateRoot<Appointment> {

    @NotNull(message = "Doctor ID is required")
    private Long doctorId;

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotNull(message = "Date is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
    private LocalDate date;

    private String reason;

    public Appointment(){}

    public Appointment(CreateAppointmentCommand command){
        this.doctorId = command.doctorId();
        this.patientId = command.patientId();
        this.date = command.date();
        this.reason = command.reason();
    }

    public Appointment updateReason(String reason) {
        this.reason = reason;
        return this;
    }

    public Appointment updateDate(LocalDate date) {
        this.date = date;
        return this;
    }
}
