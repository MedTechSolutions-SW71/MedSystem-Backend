package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Transform;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.UpdateLaboratoryCommand;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.UpdateLaboratoryResource;

public class UpdateLaboratoryCommandFromResourceAssembler {

    public static UpdateLaboratoryCommand toCommandFromResource(UpdateLaboratoryResource resource, Long id) {
        return new UpdateLaboratoryCommand(
                id,
                resource.name(),
                resource.address(),
                resource.phone(),
                resource.email()
        );
    }
}