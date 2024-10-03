package org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Aggregates;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Commands.CreateAppointmentCommand;
import org.medTechSolutions.platform.appointments_service.Appointments.Domain.Model.Entities.Specialty;
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

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    public Appointment(){}

    public Appointment(Long doctorId, Long patientId, LocalDate date, String reason, Specialty specialty) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
        this.reason = reason;
        this.specialty = specialty;
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
