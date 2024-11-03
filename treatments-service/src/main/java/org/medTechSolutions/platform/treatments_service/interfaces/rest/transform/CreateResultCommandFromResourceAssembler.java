package org.medTechSolutions.platform.treatments_service.interfaces.rest.transform;

import org.medTechSolutions.platform.treatments_service.domain.model.commands.CreateResultCommand;
import org.medTechSolutions.platform.treatments_service.interfaces.rest.resources.CreateResultResource;

public class CreateResultCommandFromResourceAssembler {
    public static CreateResultCommand toCommandFromResource(CreateResultResource resource) {
        return new CreateResultCommand(
                resource.doctorId(),
                resource.patientId(),
                resource.typeOfExam(),
                resource.resultDateTime(),
                resource.result()
        );
    }
}