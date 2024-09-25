package org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Transform;

import org.medTechSolutions.platform.profiles_service.User.Domain.Model.Commands.CreateLaboratoryCommand;
import org.medTechSolutions.platform.profiles_service.User.Interfaces.rest.Resources.CreateLaboratoryResource;

public class CreateLaboratoryCommandFromResourceAssembler {

    public static CreateLaboratoryCommand toCommandFromResource(CreateLaboratoryResource resource) {
        return new CreateLaboratoryCommand(
                resource.name(),
                resource.address(),
                resource.phone(),
                resource.email()
        );
    }
}