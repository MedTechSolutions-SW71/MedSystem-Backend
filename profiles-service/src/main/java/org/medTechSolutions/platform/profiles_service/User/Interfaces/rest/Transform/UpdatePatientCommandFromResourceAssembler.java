package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Transform;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.UpdatePatientCommand;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.UpdatePatientResource;

public class UpdatePatientCommandFromResourceAssembler {
    public static UpdatePatientCommand toCommandFromResource(UpdatePatientResource resource, Long id) {
        return new UpdatePatientCommand(
                id,
                resource.firstName(),
                resource.lastName(),
                resource.age(),
                resource.address(),
                resource.phone(),
                resource.email()
        );
    }
}
