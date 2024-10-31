package org.medTechSolutions.platform.treatments_service.domain.model.aggregates;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.medTechSolutions.platform.treatments_service.domain.model.commands.CreateResultCommand;
import org.medTechSolutions.platform.treatments_service.shared.domain.model.entities.AuditableModel;

@Getter
@Entity
@NoArgsConstructor
public class Result extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long doctorId;

    @NotNull
    private Long patientId;

    @NotNull
    private String typeOfExam;

    @NotNull
    private String resultDateTime;

    @NotNull
    private boolean result;

    public Result(CreateResultCommand command) {
        this.doctorId = command.doctorId();
        this.patientId = command.patientId();
        this.typeOfExam = command.typeOfExam();
        this.resultDateTime = command.resultDateTime();
        this.result = command.result();
    }
}
