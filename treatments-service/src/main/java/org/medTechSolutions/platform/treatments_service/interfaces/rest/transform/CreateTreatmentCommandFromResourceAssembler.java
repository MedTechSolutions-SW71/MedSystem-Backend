package org.medTechSolutions.platform.treatments_service.interfaces.rest.transform;

import org.medTechSolutions.platform.treatments_service.domain.model.commands.CreateTreatmentCommand;
import org.medTechSolutions.platform.treatments_service.interfaces.rest.resources.CreateTreatmentResource;

public class CreateTreatmentCommandFromResourceAssembler {
    public static CreateTreatmentCommand toCommandFromResource(CreateTreatmentResource resource) {
        return new CreateTreatmentCommand(
                resource.patientId(),
                resource.doctorId(),
                resource.description(),
                resource.examResultIds()
        );
    }
}