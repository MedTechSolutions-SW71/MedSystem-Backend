package org.medTechSolutions.platform.exams_service.Exams.domain.model.aggregates;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.medTechSolutions.platform.exams_service.Exams.domain.model.commands.CreateExamCommand;
import org.medTechSolutions.platform.exams_service.Shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.Date;

@Getter
@Entity
@Setter
public class Exam extends AuditableAbstractAggregateRoot<Exam> {

    @NotNull(message = "Doctor ID is required")
    private Long doctorId;
    @NotNull(message = "Doctor ID is required")
    private Long patientId;
    @NotBlank
    private String examType;
    @NotNull(message = "Exam date is required")
    private Date examDate;
    @NotNull
    private Date examResultDate;
    @NotNull
    private Boolean examResult;

    //private String examUrl;

    public Exam(){}

    public Exam(CreateExamCommand command){
        this.doctorId = command.doctorId();
        this.patientId = command.patientId();
        this.examType = command.examType();
        this.examDate = command.examDate();
        this.examResultDate = command.examResultDate();
        this.examResult = false;
    }

    public Exam updateExamResult() {
        this.examResult = true;
        return this;
    }
}
