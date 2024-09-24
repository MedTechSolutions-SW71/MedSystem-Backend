package org.medTechSolutions.platform.treatments_service.interfaces.rest.transform;

import org.medTechSolutions.platform.treatments_service.domain.model.commands.DeleteTreatmentCommand;

public class DeleteTreatmentCommandFromResourceAssembler {
    public static DeleteTreatmentCommand toCommandFromResource(Long treatmentId) {
        return new DeleteTreatmentCommand(treatmentId);
    }
}