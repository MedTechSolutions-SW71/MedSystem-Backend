package org.medTechSolutions.platform.treatments_service.application.internal.queryservices;


import org.medTechSolutions.platform.treatments_service.domain.model.aggregates.Treatment;
import org.medTechSolutions.platform.treatments_service.domain.model.queries.GetAllTreatmentsQuery;
import org.medTechSolutions.platform.treatments_service.domain.model.queries.GetTreatmentByPatientIdQuery;
import org.medTechSolutions.platform.treatments_service.domain.services.TreatmentQueryService;
import org.medTechSolutions.platform.treatments_service.infrastucture.persistence.jpa.repositories.TreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentQueryServiceImpl implements TreatmentQueryService {
    private final TreatmentRepository treatmentRepository;

    public TreatmentQueryServiceImpl(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public List<Treatment> handle(GetAllTreatmentsQuery query) {
        return treatmentRepository.findAll();
    }

    @Override
    public List<Treatment> handle(GetTreatmentByPatientIdQuery query) {
        return treatmentRepository.findByPatientId(query.patientId());
    }
}