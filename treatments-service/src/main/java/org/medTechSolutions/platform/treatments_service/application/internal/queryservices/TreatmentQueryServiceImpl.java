package org.medTechSolutions.platform.treatments_service.application.internal.queryservices;


import org.medTechSolutions.platform.treatments_service.domain.model.aggregates.Treatment;
import org.medTechSolutions.platform.treatments_service.domain.model.queries.GetAllTreatmentsQuery;
import org.medTechSolutions.platform.treatments_service.domain.model.queries.GetTreatmentByIdQuery;
import org.medTechSolutions.platform.treatments_service.domain.services.TreatmentQueryService;
import org.medTechSolutions.platform.treatments_service.infrastucture.persistence.jpa.repositories.TreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentQueryServiceImpl extends TreatmentQueryService {


    public TreatmentQueryServiceImpl(TreatmentRepository treatmentRepository) {
        super(treatmentRepository);
    }

    @Override
    public Optional<Treatment> handle(GetTreatmentByIdQuery query) {

        return treatmentRepository.findById(query.treatmentId());
    }

    @Override
    public List<Treatment> handle(GetAllTreatmentsQuery query) {
        return treatmentRepository.findAll();
    }

    @Override
    public Treatment getTreatmentById(Long treatmentId) {
        return treatmentRepository.findById(treatmentId)
                .orElseThrow(() -> new IllegalArgumentException("El tratamiento no existe"));
    }

    @Override
    public List<Treatment> getTreatmentsByPatientId(Long patientId) {
        return treatmentRepository.findByPatientId(patientId);
    }

    @Override
    public List<Treatment> getTreatmentsByDoctorId(Long doctorId) {
        return treatmentRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Treatment> getCompletedTreatments() {
        return treatmentRepository.findByIsCompleted(true);
    }

    @Override
    public List<Treatment> getPendingTreatments() {
        return treatmentRepository.findByIsCompleted(false);
    }

    @Override
    public List<String> getExamResultsByTreatmentId(Long treatmentId) {
        Treatment treatment = treatmentRepository.findById(treatmentId)
                .orElseThrow(() -> new IllegalArgumentException("El tratamiento no existe"));
        return treatment.getExamResultIds().stream()
                .map(String::valueOf)
                .toList();
    }
}