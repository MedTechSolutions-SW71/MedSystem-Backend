package org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Aggregates;

import jakarta.persistence.Entity;
import lombok.Getter;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Commands.CreateAppointmentCommand;
import org.medTechSolutions.platform.appointments_service.Shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Getter
public class Appointment extends AuditableAbstractAggregateRoot<Appointment> {
    private Long doctorId;
    private Long patientId;

    private String date;

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

    public Appointment updateDate(String date) {
        this.date = date;
        return this;
    }
}
