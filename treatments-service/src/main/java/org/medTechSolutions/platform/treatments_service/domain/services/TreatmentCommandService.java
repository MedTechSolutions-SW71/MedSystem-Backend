package org.medTechSolutions.platform.treatments_service.domain.services;


import org.medTechSolutions.platform.treatments_service.domain.model.aggregates.Treatment;
import org.medTechSolutions.platform.treatments_service.domain.model.commands.*;

import java.util.Optional;

public interface TreatmentCommandService {
    Long handle(CreateTreatmentCommand command);
    Optional<Treatment> handle(UpdateTreatmentCommand command);
    void handle(DeleteTreatmentCommand command);
    void completeTreatment(CompleteTreatmentCommand command);
    void addExamResult(AddExamResultToTreatmentCommand command);
}