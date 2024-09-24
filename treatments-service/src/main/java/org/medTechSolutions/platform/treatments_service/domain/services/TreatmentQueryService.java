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

    // Si la clase es abstracta, no necesitas este constructor
    public TreatmentQueryService(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    public List<Treatment> handle(GetAllTreatmentsQuery query) {
        return treatmentRepository.findAll();
    }

    public Optional<Treatment> handle(GetTreatmentByIdQuery query) {
        // Si GetTreatmentByIdQuery es un 'record', accede al campo con su nombre.
        return treatmentRepository.findById(query.treatmentId());
    }

    // Métodos abstractos que serán implementados por las subclases
    public abstract Treatment getTreatmentById(Long treatmentId);

    public abstract List<Treatment> getTreatmentsByPatientId(Long patientId);

    public abstract List<Treatment> getTreatmentsByDoctorId(Long doctorId);

    public abstract List<Treatment> getCompletedTreatments();

    public abstract List<Treatment> getPendingTreatments();

    public abstract List<String> getExamResultsByTreatmentId(Long treatmentId);
}