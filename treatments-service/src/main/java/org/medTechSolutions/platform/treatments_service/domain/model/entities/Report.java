package org.medTechSolutions.platform.treatments_service.domain.model.entities;


import jakarta.persistence.*;
import lombok.Getter;
import org.medTechSolutions.platform.treatments_service.domain.model.commands.CreateReportCommand;
import org.medTechSolutions.platform.treatments_service.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
public class Report extends AuditableAbstractAggregateRoot<Report> {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reason", nullable = false)
    @Getter
    private String reason;

    @Column(name = "date", nullable = false, updatable = false)
    @Getter
    private String date;

    @Column(name = "patient_id", nullable = false, updatable = false)
    @Getter
    private Long patientId;

    protected Report() {}

    public Report(String reason, String date, Long patientId) {
        this.reason = reason;
        this.date = date;
        this.patientId = patientId;
    }

    public Report(CreateReportCommand command) {
        this();
        this.reason = command.reason();
        this.date = command.date();
        this.patientId = command.patientId();
    }
}