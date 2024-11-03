package org.medTechSolutions.platform.treatments_service.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.medTechSolutions.platform.treatments_service.domain.model.commands.CreateTreatmentCommand;
import org.medTechSolutions.platform.treatments_service.domain.model.valueobjects.Period;
import org.medTechSolutions.platform.treatments_service.shared.domain.model.entities.AuditableModel;



@Getter
@Entity
@NoArgsConstructor
public class Treatment extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "treatment_name", nullable = false)
    private String treatmentName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "startDate", column = @Column(name = "start_date")),
            @AttributeOverride(name = "endDate", column = @Column(name = "end_date"))
    })
    private Period period;

    public Treatment(CreateTreatmentCommand command) {
        this.treatmentName = command.treatmentName();
        this.description = command.description();
        this.patientId = command.patientId();
        this.period = new Period(command.startDate(), command.endDate());
    }

    public String getPeriod() {
        return period.getPeriod();
    }
}