package org.medTechSolutions.platform.treatments_service.interfaces.rest.resources;

import java.time.LocalDateTime;
import java.util.List;

public class TreatmentResource {

    private Long id;
    private Long patientId;
    private Long doctorId;
    private String description;
    private List<Long> examResultIds;
    private boolean completed;

    public TreatmentResource(Long id, Long patientId, Long doctorId, String description, List<Long> examResultIds, boolean completed) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.description = description;
        this.examResultIds = examResultIds;
        this.completed = completed;
    }

    // Getters
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

    public List<Long> getExamResultIds() {
        return examResultIds;
    }

    public boolean isCompleted() {
        return completed;
    }
}