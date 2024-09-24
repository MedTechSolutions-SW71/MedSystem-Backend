package org.medTechSolutions.platform.treatments_service.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.medTechSolutions.platform.treatments_service.shared.domain.model.entities.AuditableModel;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
@AllArgsConstructor
public class Treatment extends AuditableModel {  // Hereda de AuditableModel

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long patientId;

    @Column(nullable = false)
    private Long doctorId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean isCompleted;

    @OneToMany // Cambiado a relación con ExamResult
    private List<ExamResult> examResults = new ArrayList<>();

    @ElementCollection
    private List<Long> examResultIds = new ArrayList<>();

    protected Treatment() {
    }

    public Treatment(Long patientId, Long doctorId, String description, List<Long> examResultIds) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.description = description;
        this.examResultIds = new ArrayList<>(examResultIds);
        this.isCompleted = false;
    }


    public Long getId() {
        return id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public String getDescription() {
        return description;
    }


    public boolean isCompleted() {
        return isCompleted;
    }

    public List<Long> getExamResultIds() {
        return examResultIds;
    }

    public void updateDetails(String description, List<Long> examResultIds) {
        this.description = description;
        this.examResultIds = new ArrayList<>(examResultIds);
    }

    public void completeTreatment() {
        this.isCompleted = true;
    }

    public void addExamResult(Long examResultId) {
        this.examResultIds.add(examResultId);
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}