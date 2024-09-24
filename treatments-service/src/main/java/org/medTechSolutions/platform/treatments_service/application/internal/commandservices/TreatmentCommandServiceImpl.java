package org.medTechSolutions.platform.treatments_service.application.internal.commandservices;


import org.medTechSolutions.platform.treatments_service.domain.model.aggregates.Treatment;
import org.medTechSolutions.platform.treatments_service.domain.model.commands.*;
import org.medTechSolutions.platform.treatments_service.domain.services.TreatmentCommandService;
import org.medTechSolutions.platform.treatments_service.infrastucture.persistence.jpa.repositories.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class TreatmentCommandServiceImpl implements TreatmentCommandService {

    private final TreatmentRepository treatmentRepository;

    @Autowired
    public TreatmentCommandServiceImpl(TreatmentRepository treatmentRepository) {
        this.treatmentRepository = treatmentRepository;
    }

    @Override
    public Long handle(CreateTreatmentCommand command) {
        Treatment treatment = new Treatment(
                command.patientId(),
                command.doctorId(),
                command.description(),
                command.examResultIds()
        );
        try {
            treatmentRepository.save(treatment);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al guardar el tratamiento: " + e.getMessage());
        }
        return treatment.getId();
    }

    @Override
    public Optional<Treatment> handle(UpdateTreatmentCommand command) {
        Optional<Treatment> result = treatmentRepository.findById(command.treatmentId());
        if (result.isEmpty()) throw new IllegalArgumentException("El tratamiento no existe");
        Treatment treatmentToUpdate = result.get();

        treatmentToUpdate.updateDetails(
                command.description(),
                command.examResultIds()
        );
        treatmentToUpdate.setCompleted(command.isCompleted());

        try {
            treatmentRepository.save(treatmentToUpdate);
            return Optional.of(treatmentToUpdate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al actualizar el tratamiento: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteTreatmentCommand command) {
        if (!treatmentRepository.existsById(command.treatmentId())) {
            throw new IllegalArgumentException("El tratamiento no existe");
        }
        try {
            treatmentRepository.deleteById(command.treatmentId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al eliminar el tratamiento: " + e.getMessage());
        }
    }

    @Override
    public void completeTreatment(CompleteTreatmentCommand command) {
        Optional<Treatment> result = treatmentRepository.findById(command.treatmentId());
        if (result.isEmpty()) throw new IllegalArgumentException("El tratamiento no existe");
        Treatment treatment = result.get();
        treatment.completeTreatment();
        treatmentRepository.save(treatment);
    }

    @Override
    public void addExamResult(AddExamResultToTreatmentCommand command) {
        Optional<Treatment> result = treatmentRepository.findById(command.treatmentId());
        if (result.isEmpty()) throw new IllegalArgumentException("El tratamiento no existe");
        Treatment treatment = result.get();
        treatment.addExamResult(command.examResultId());
        treatmentRepository.save(treatment);
    }
}