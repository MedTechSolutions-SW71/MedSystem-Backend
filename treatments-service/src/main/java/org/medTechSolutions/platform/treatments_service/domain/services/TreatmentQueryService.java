package org.medTechSolutions.platform.treatments_service.domain.services;


import org.medTechSolutions.platform.treatments_service.domain.model.aggregates.Treatment;
import org.medTechSolutions.platform.treatments_service.domain.model.queries.GetAllTreatmentsQuery;
import org.medTechSolutions.platform.treatments_service.domain.model.queries.GetTreatmentByIdQuery;
import org.medTechSolutions.platform.treatments_service.infrastucture.persistence.jpa.repositories.TreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class TreatmentQueryService {

    protected final TreatmentRepository treatmentRepository;


    public TreatmentQueryService(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    public List<Treatment> handle(GetAllTreatmentsQuery query) {
        return treatmentRepository.findAll();
    }

    public Optional<Treatment> handle(GetTreatmentByIdQuery query) {

        return treatmentRepository.findById(query.treatmentId());
    }


    public abstract Treatment getTreatmentById(Long treatmentId);

    public abstract List<Treatment> getTreatmentsByPatientId(Long patientId);

    public abstract List<Treatment> getTreatmentsByDoctorId(Long doctorId);

    public abstract List<Treatment> getCompletedTreatments();

    public abstract List<Treatment> getPendingTreatments();

    public abstract List<String> getExamResultsByTreatmentId(Long treatmentId);
}