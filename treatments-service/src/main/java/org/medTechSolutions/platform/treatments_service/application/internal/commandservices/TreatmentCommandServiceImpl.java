package org.medTechSolutions.platform.treatments_service.application.internal.commandservices;


import org.medTechSolutions.platform.treatments_service.domain.model.aggregates.Treatment;
import org.medTechSolutions.platform.treatments_service.domain.model.commands.*;
import org.medTechSolutions.platform.treatments_service.domain.services.TreatmentCommandService;
import org.medTechSolutions.platform.treatments_service.infrastucture.persistence.jpa.repositories.TreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TreatmentCommandServiceImpl implements TreatmentCommandService {
    private final TreatmentRepository treatmentRepository;

    public TreatmentCommandServiceImpl(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public Optional<Treatment> handle(CreateTreatmentCommand command) {
        if (treatmentRepository.existsByTreatmentNameAndPatientId(command.treatmentName(), command.patientId()))
            throw new IllegalArgumentException("Treatment " + command.treatmentName() + " already exists.");
        var treatment = new Treatment(command);
        treatmentRepository.save(treatment);
        return Optional.of(treatment);
    }

    @Override
    public void handle(DeleteTreatmentCommand command) {
        var treatment = treatmentRepository.findByTreatmentName(String.valueOf(command.treatmentId()));
        treatment.ifPresent(treatmentRepository::delete);
    }
}