package org.medTechSolutions.platform.exams_service.Exams.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.medTechSolutions.platform.exams_service.Exams.domain.model.commands.CreateExamCommand;
import org.medTechSolutions.platform.exams_service.Shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
    private LocalDate examDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Lima")
    @LastModifiedDate
    private LocalDate examResultDate;

    private Boolean examResultsReady;

    private String examResultsUrl;

    public Exam(){}

    public Exam(CreateExamCommand command){
        this.doctorId = command.doctorId();
        this.patientId = command.patientId();
        this.examType = command.examType();
        this.examDate = command.examDate();
        this.examResultsReady = false;
    }

    public Exam updateExamResult(Boolean examResultsReady, String examResultsUrl) {
        this.examResultsReady = examResultsReady;
        this.examResultsUrl = examResultsUrl;
        return this;
    }
}
