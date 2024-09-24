package org.medTechSolutions.platform.treatments_service.interfaces.rest.transform;

import org.medTechSolutions.platform.treatments_service.domain.model.commands.UpdateTreatmentCommand;
import org.medTechSolutions.platform.treatments_service.interfaces.rest.resources.UpdateTreatmentResource;

public class UpdateTreatmentCommandFromResourceAssembler {
    public static UpdateTreatmentCommand toCommandFromResource(UpdateTreatmentResource resource) {
        return new UpdateTreatmentCommand(
                resource.treatmentId(),
                resource.patientId(),
                resource.doctorId(),
                resource.description(),
                resource.examResultIds(),
                resource.isCompleted()
        );
    }
}