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

    // Constructor correcto que llama al constructor de la clase base
    public TreatmentQueryServiceImpl(TreatmentRepository treatmentRepository) {
        super(treatmentRepository);  // Llamar al constructor de la clase base
    }

    @Override
    public Optional<Treatment> handle(GetTreatmentByIdQuery query) {
        // Buscar tratamiento por ID
        return treatmentRepository.findById(query.treatmentId());
    }

    @Override
    public List<Treatment> handle(GetAllTreatmentsQuery query) {
        // Retornar todos los tratamientos
        return treatmentRepository.findAll();
    }

    @Override
    public Treatment getTreatmentById(Long treatmentId) {
        // Obtener tratamiento por su ID
        return treatmentRepository.findById(treatmentId)
                .orElseThrow(() -> new IllegalArgumentException("El tratamiento no existe"));
    }

    @Override
    public List<Treatment> getTreatmentsByPatientId(Long patientId) {
        // Obtener todos los tratamientos asociados a un paciente específico
        return treatmentRepository.findByPatientId(patientId);
    }

    @Override
    public List<Treatment> getTreatmentsByDoctorId(Long doctorId) {
        // Obtener todos los tratamientos asociados a un doctor específico
        return treatmentRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Treatment> getCompletedTreatments() {
        // Obtener todos los tratamientos que están completados
        return treatmentRepository.findByIsCompleted(true);
    }

    @Override
    public List<Treatment> getPendingTreatments() {
        // Obtener todos los tratamientos que aún están pendientes (no completados)
        return treatmentRepository.findByIsCompleted(false);
    }

    @Override
    public List<String> getExamResultsByTreatmentId(Long treatmentId) {
        // Obtener los IDs de los resultados de exámenes asociados a un tratamiento
        Treatment treatment = treatmentRepository.findById(treatmentId)
                .orElseThrow(() -> new IllegalArgumentException("El tratamiento no existe"));
        return treatment.getExamResultIds().stream()
                .map(String::valueOf) // Convertir los IDs de los resultados a String
                .toList();
    }
}